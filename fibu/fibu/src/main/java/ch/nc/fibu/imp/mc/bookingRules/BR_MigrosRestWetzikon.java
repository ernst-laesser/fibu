package ch.nc.fibu.imp.mc.bookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_MigrosRestWetzikon extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("Migros MR Wetzikon")) {
			super.createBooking(rawBooking);
			booking.setSoll(5820);
			booking.setHaben(2010);
			booking.setText("Migros Restaurant Wetzikon");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}

	
}
