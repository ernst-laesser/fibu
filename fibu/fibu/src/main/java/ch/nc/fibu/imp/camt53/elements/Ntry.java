package ch.nc.fibu.imp.camt53.elements;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import ch.nc.fibu.imp.camt53.RawBooking;

public class Ntry extends BaseElement {

	@Override
	public String getElementName() {
		return "Ntry";
	}

	@Override
	public void startElement(String qName, Attributes attributes) throws SAXException {
		context.setXmlState(XmlState.Ntry);
		context.setCurrentBooking(new RawBooking());
		context.setContentElement(null);
		/*
		 * System.out.println(
		 * "Start Ntry Element -------------------------------------------------------------------------------"
		 * );
		 */
	}

	@Override
	public void content(String content) throws SAXException {

	}

	@Override
	public void endElement(String qName) throws SAXException {
		context.setXmlState(XmlState.Undef);
		/*
		 * System.out.println(
		 * "Content of RawBooking -------------------------------------------------------------------------------"
		 * );
		 */
		RawBooking booking = context.getCurrentBooking();
		if (booking != null) {
			System.out.println(booking.toString());
			context.getBookingHandler().processBooking(booking);
		}

		/*
		 * System.out.println(
		 * "Content of RawBooking -------------------------------------------------------------------------------"
		 * );
		 */

	}

}
