package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_Default extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		super.createBooking(rawBooking);
		if (rawBooking.isExpense()) {
			booking.setSoll(0);
			booking.setHaben(1010);
		} else {
			booking.setSoll(1010);
			booking.setHaben(0);
		}
		/*
		 * if(rawBooking.getAdditionalInfo().length() <= 50) {
		 * booking.setText(rawBooking.getAdditionalInfo()); } else {
		 * booking.setText(rawBooking.getAdditionalInfo().substring(0, 49)); }
		 */
		booking.setText(rawBooking.getText());
		writer.addBooking(booking);
		return true;
	}
}
