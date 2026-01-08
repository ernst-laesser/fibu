package ch.nc.fibu.imp.jour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.nc.fibu.imp.Booking;

public class JournalBooking {

	private Date date;
	private int soll;
	private int haben;
	private float betrag;
	// private String text;
	

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");

	public void setDate(String dateString) throws ParseException {
		date = simpleDateFormat.parse(dateString);
	}

	public void setSoll(String sollString) {
		soll = Integer.parseInt(sollString);
	}

	public void setHaben(String habenString) {
		haben = Integer.parseInt(habenString);
	}

	public void setAmount(String amountString) {
		betrag = Float.parseFloat(amountString);
	}

	public boolean isRelevant(int accountNr) {		
		return (soll == accountNr  ||  haben == accountNr);
	}

	public boolean isDouble(Booking booking) throws ParseException {
		
		boolean sameDate = date.getTime() == booking.getDate().getTime();
		float delta = Math.abs(booking.getBetrag() - betrag);

		if (sameDate && delta < 0.01) {
			return true;
		}

		return false;

	}

}
