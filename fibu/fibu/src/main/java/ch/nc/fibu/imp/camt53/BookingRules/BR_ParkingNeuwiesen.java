package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_ParkingNeuwiesen extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("PARKING NEUWIESEN WINTERTHUR")) {
			super.createBooking(rawBooking);
			booking.setSoll(6200);
			booking.setHaben(1010);
			booking.setText("Parking Neuwiesen Winterthur");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
	}
}
