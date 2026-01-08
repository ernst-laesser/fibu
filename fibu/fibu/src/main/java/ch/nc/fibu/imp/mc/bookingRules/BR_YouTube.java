package ch.nc.fibu.imp.mc.bookingRules;
import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_YouTube extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("YouTube")) {
			super.createBooking(rawBooking);
			booking.setSoll(6502);
			booking.setHaben(2010);
			booking.setText("Google YouTube");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}
}
