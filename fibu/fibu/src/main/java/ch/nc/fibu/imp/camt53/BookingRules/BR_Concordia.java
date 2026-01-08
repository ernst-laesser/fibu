package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Concordia extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("CONCORDIA VERSICHERUNGEN AG")) {
			if (rawBooking.getAmount()>(float)800.0) { //TODO Betrag besser eingrenzen
				super.createBooking(rawBooking);
				booking.setSoll(4901);
				booking.setHaben(1010);
				booking.setText("Concordia, Pr�mie");
				writer.addBooking(booking);
				return true;
			} else {
				super.createBooking(rawBooking);
				booking.setSoll(5401);
				booking.setHaben(1010);
				booking.setText("Concordia, Mein Kostenanteil");
				writer.addBooking(booking);
				return true;
			}
		} else {
			return false;
		}		
	}
}
