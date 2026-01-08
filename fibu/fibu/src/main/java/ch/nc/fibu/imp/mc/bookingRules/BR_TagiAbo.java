package ch.nc.fibu.imp.mc.bookingRules;
import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_TagiAbo extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("tamedia.ch")) {
			super.createBooking(rawBooking);
			booking.setSoll(6502);
			booking.setHaben(2010);
			booking.setText("Tagi Abonnement");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}
}
