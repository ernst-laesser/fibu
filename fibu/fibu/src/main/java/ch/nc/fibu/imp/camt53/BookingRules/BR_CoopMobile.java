package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_CoopMobile extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("COOP MOBILE")) {
			super.createBooking(rawBooking);
			booking.setSoll(6511);
			booking.setHaben(1010);
			booking.setText("Coop Mobile");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
	}
}
