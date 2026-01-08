package ch.nc.fibu.imp.mc.bookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Apple extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("APPLE.COM")) {
			super.createBooking(rawBooking);
			float amount = rawBooking.getAmount();
			if(amount >=13.00) {
				booking.setSoll(6502);
				booking.setText("Apple iTunes");
			} else {
				booking.setSoll(6501);
				booking.setText("Apple iDrive");
			}
			booking.setHaben(2010);
			
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}
}
