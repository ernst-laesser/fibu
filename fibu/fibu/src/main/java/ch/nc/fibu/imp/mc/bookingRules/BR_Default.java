package ch.nc.fibu.imp.mc.bookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Default extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		super.createBooking(rawBooking);
		if (rawBooking.isExpense()) {
			booking.setSoll(0);
			booking.setHaben(2010);
		} else {
			booking.setSoll(2010);
			booking.setHaben(0);
		}
		booking.setText(rawBooking.getText());
		writer.addBooking(booking);
		return true;
	}
}
