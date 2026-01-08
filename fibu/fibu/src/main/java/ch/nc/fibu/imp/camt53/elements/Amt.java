package ch.nc.fibu.imp.camt53.elements;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import ch.nc.fibu.imp.camt53.RawBooking;

public class Amt extends BaseElement {

	@Override
	public String getElementName() {
		return "Amt";
	}

	@Override
	public void startElement(String qName, Attributes attributes) throws SAXException {
		if(context.isXmlState(XmlState.Ntry)){
			context.setContentElement(this);
		}
	}

	@Override
	public void content(String content) throws SAXException {
		RawBooking booking=context.getCurrentBooking();
		if(booking!=null){
			booking.setAmount(content);
		}
	}

	@Override
	public void endElement(String qName) throws SAXException {
		if(context.isXmlState(XmlState.Ntry)){
			context.setContentElement(null);
		}
	}

}
