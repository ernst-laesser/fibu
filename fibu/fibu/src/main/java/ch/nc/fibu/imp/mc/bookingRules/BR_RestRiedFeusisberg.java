package ch.nc.fibu.imp.mc.bookingRules;
import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_RestRiedFeusisberg extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("Landgasthof Ried")) {
			super.createBooking(rawBooking);
			booking.setSoll(1280);
			booking.setHaben(2010);
			booking.setText("Rest. Ried mit Mutter");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}
		
	}
}
