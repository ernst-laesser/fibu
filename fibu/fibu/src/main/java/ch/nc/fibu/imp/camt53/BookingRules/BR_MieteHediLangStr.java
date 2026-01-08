package ch.nc.fibu.imp.camt53.BookingRules;

import ch.nc.fibu.imp.BookingRuleBase;
import ch.nc.fibu.imp.RawBookingIF;

public class BR_MieteHediLangStr extends BookingRuleBase {

	@Override
	public boolean processBooking(RawBookingIF rawBooking) {
		if (rawBooking.isExpense() && rawBooking.getText().contains("ASGA PENSIONSKASSE GENOSSENSCHAFT")) {
			super.createBooking(rawBooking);
			booking.setSoll(9997);
			booking.setHaben(1010);
			booking.setText("Miete Hedi-Lang-Str. 7");
			writer.addBooking(booking);

			super.createBooking(rawBooking);
			booking.setSoll(6200);
			booking.setHaben(9997);
			booking.setBetrag((float) 150.0);
			booking.setText("Garage Hedi-Lang-Str. 7");
			writer.addBooking(booking);

			super.createBooking(rawBooking);
			booking.setSoll(4410);
			booking.setHaben(9997);
			booking.setBetrag((float)2216.0); //TODO Betrag berechnen.
			booking.setText("Wohnung  Hedi-Lang-Str. 7");
			writer.addBooking(booking);
			return true;
		} else {
			return false;
		}

	}
}
