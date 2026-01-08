package ch.nc.fibu.imp.mm.receipt;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import ch.nc.fibu.imp.mm.item.ItemHandler;

public class Receipt {
	private String filiale;
	private boolean restaurant=false;
	private Iterator<String> lines;
	private ArrayList<Article> articleList = new ArrayList<Article>();
	private float subTotal;
	private float total;
	private Date date;
	private String paymentType;
	
	
	public Receipt() {
	}
	
	public String getFiliale() {
		return filiale;
	}

	public boolean isRestaurant() {
		return restaurant;
	}

	public ArrayList<Article> getItemList() {
		return articleList;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public float getTotal() {
		return total;
	}

	public Date getDate() {
		return date;
	}

	public String getPaymentType() {
		return paymentType;
	}



	public void extractData(Iterator<String> lines) throws ParseException {
		this.lines=lines;
		
		extractFiliale();
		//System.out.println(getFiliale());
		if(!restaurant) {
			extractArticles();
		}
		extractPayment();
		extractDate();
	}
	
	private void extractFiliale() {
		filiale = lines.next().trim();	
		if(filiale.startsWith("MR")) {
			restaurant=true;
		}
	}
	
	private void extractArticles() {
		while(!lines.next().startsWith("Artikelbezeichnung")){}
		while(lines.hasNext()) {
			String line =lines.next();
			if(line.startsWith("------")) {
				break;				
			} else if(line.startsWith("Zwischentotal")) {
				subTotal=Float.parseFloat((String) line.subSequence(45, 53));
				break;
				
			} else {
				Article article = new Article();
				article.name=line.substring(0, 21);
				article.price=Float.parseFloat((String) line.subSequence(45, 53));
				articleList.add(article);
				// temporay to test ItemHandler
				
				System.out.println(article.toString());
			}
		}
	}
	
	private void extractPayment() {
		while(!lines.next().startsWith("Total CHF")){};
		String line = lines.next();
		paymentType = line.substring(0, 21).trim();
		total = Float.parseFloat((String) line.subSequence(45, 53));		
	}
	
	private void extractDate() throws ParseException {
		while(!lines.next().contains("Besten Dank für")){};
		while(!lines.next().startsWith("Filiale")){};
		String line = lines.next();
		if(line.isBlank()) { // just in case there is line break before the date line
			line=lines.next();
		}
		String dateString =line.substring(29, 40).trim();
		date=ItemHandler.dateFormat.parse(dateString);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("Filiale=" + filiale + "; ");
		sb.append("Datum =" + ItemHandler.dateFormat.format(date) + "; ");
		sb.append("Anzahl Artikel =" + articleList.size() + "; ");
		sb.append("Zwischensumme =" + subTotal + "; ");
		sb.append("Zahlungsart =" + paymentType + "; ");
		sb.append("Total =" + total + "; ");
		
		return sb.toString();
		
	}
	

}
