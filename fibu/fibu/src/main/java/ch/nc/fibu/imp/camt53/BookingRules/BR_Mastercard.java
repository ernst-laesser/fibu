package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Mastercard extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("ZAHLUNGSEMPF�NGER: POSTFINANCE, KREDITKARTEN")) {
			super.createBooking(rawBooking);
			booking.setSoll(2010);
			booking.setHaben(1010);
			booking.setText("Kreditkarten-Rechung MC-PF");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}
}
