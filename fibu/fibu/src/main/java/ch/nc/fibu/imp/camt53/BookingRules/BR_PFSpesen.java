package ch.nc.fibu.imp.camt53.BookingRules;
import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_PFSpesen extends BookingRuleBase {
	
	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("PREIS F�R BANKPAKET SMART")) {
			super.createBooking(rawBooking);
			booking.setSoll(6800);
			booking.setHaben(1010);
			booking.setText(rawBooking.getText());
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}	
	}
}
