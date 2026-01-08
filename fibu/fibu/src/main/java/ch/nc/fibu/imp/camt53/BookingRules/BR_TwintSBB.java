package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_TwintSBB extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("TWINT KAUF/DIENSTLEISTUNG")
				&& rawBooking.getText().contains("SBB MOBILE BERN")) {
			super.createBooking(rawBooking);
			booking.setSoll(6100);
			booking.setHaben(1010);
			booking.setText("SBB/ZVV (TWINT)");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
	}
}
