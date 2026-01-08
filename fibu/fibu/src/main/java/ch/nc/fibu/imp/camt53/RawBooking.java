package ch.nc.fibu.imp.camt53;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.nc.fibu.imp.RawBookingIF;

public class RawBooking implements RawBookingIF{
	private Date date;
	private String creditDebit;
	private float amount;
	private String text;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public Date getDate() {
		return date;
	}
	public void setBookingDate(String bookingDate) {
		
		try {
			this.date = simpleDateFormat.parse(bookingDate);
		} catch (ParseException e) {
			System.out.println("RawBooking, problem parsing date: "+bookingDate);
		}
	}
	
	public String getCreditDebit() {
		return creditDebit;
	}
	public void setCreditDebit(String creditDebit) {
		this.creditDebit = creditDebit;
	}
	public boolean isExpense() {
		return "DBIT".compareTo(creditDebit)==0;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = Float.parseFloat(amount);
	}
	public String getText() {
		return text;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.text = additionalInfo;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("RawBooking: ");
		s.append("bookingDate=" + date);
		s.append(" crditDebit="+creditDebit);
		s.append(" amount="+amount);
		s.append(" text="+ text);
		
		return s.toString();
	}
	
}
