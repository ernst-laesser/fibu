package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_AHV extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (!rawBooking.isExpense() && rawBooking.getText().contains("AUSGLEICHSKASSE ZUG")) {
			super.createBooking(rawBooking);
			booking.setSoll(1010);
			booking.setHaben(3400);
			booking.setText("AHV Zug");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
	}
}
