package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Pensionskasse extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (!rawBooking.isExpense() && rawBooking.getText().contains("PENSIONSKASSE")
				&& rawBooking.getText().contains("C REDIT SUISSE GROUP")) {
			super.createBooking(rawBooking);
			booking.setSoll(1010);
			booking.setHaben(3411);
			booking.setText("PK CS");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}

	}
}
