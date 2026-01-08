package ch.nc.fibu.imp.camt53.elements;

import ch.nc.fibu.imp.BookingHandler;
import ch.nc.fibu.imp.camt53.RawBooking;

public class ProcessingContext {
	
	private RawBooking currentBooking;
	private XmlState xmlState;
	private BaseElement contentElement;
	private BookingHandler bookingHandler;

	public BaseElement getContentElement() {
		return contentElement;
	}

	public void setContentElement(BaseElement contentElement) {
		this.contentElement = contentElement;
	}

	public XmlState getXmlState() {
		return xmlState;
	}

	public void setXmlState(XmlState xmlState) {
		this.xmlState = xmlState;
	}
	
	public boolean isXmlState(XmlState state) {
		return xmlState == state;
	}

	public RawBooking getCurrentBooking() {
		return currentBooking;
	}

	public void setCurrentBooking(RawBooking currentBooking) {
		this.currentBooking = currentBooking;
	}

	public void setBookingHandler(ch.nc.fibu.imp.BookingHandler bookingHandler) {
		this.bookingHandler = bookingHandler;
	}

	public BookingHandler getBookingHandler() {
		return bookingHandler;
	};	

}
