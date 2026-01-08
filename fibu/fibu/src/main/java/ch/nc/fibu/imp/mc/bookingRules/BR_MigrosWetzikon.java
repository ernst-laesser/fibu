package ch.nc.fibu.imp.mc.bookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_MigrosWetzikon extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("Migros MMM Wetzikon")) {
			super.createBooking(rawBooking);
			booking.setSoll(9981);
			booking.setHaben(2010);
			booking.setText("Migros Wetzikon");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}
}
