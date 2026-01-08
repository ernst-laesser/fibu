package ch.nc.fibu.imp;


public abstract class BookingRuleBase {

	protected BookingWriter writer;
	protected Booking booking;

	public void setWriter(BookingWriter writer) {
		this.writer = writer;
	}

	public void createBooking(RawBookingIF rawBooking) {
		this.booking = new Booking();
		booking.setDate(rawBooking.getDate());
		booking.setBetrag(rawBooking.getAmount());
	}

	abstract public boolean processBooking(RawBookingIF rawBooking);

}
