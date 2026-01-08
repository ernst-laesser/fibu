package ch.nc.fibu.imp.mm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.nc.fibu.imp.RawBookingIF;

public class RawBooking implements RawBookingIF {
	private Date date;
	private float amount;
	private String text;
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(String bookingDate) {
		try {
			this.date = simpleDateFormat.parse(bookingDate);
		} catch (ParseException e) {
			System.out.println("RawBooking, problem parsing date: "+bookingDate);
		}
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = Float.parseFloat(amount);;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		StringBuilder s = new StringBuilder("RawBooking: ");
		s.append("date=" + date);
		s.append(" amount="+amount);
		s.append(" text="+ text);		
		return s.toString();
	}

	public boolean isExpense() {
		return true;
	}

}
