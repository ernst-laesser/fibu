package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Baarbezug extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("BARGELDBEZUG")) {
			super.createBooking(rawBooking);
			booking.setSoll(1000);
			booking.setHaben(1010);
			booking.setText("Mein Baarbezug");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}		
	}
}
