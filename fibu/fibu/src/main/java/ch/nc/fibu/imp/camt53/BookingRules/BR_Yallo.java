package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Yallo extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("SUNRISE GMBH / YALLO")) {
			super.createBooking(rawBooking);
			booking.setSoll(6511);
			booking.setHaben(1010);
			booking.setText("Sunrise, yallo Internet");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}

	}
}
