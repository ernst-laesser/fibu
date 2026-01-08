package ch.nc.fibu.imp.camt53.elements;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import ch.nc.fibu.imp.camt53.RawBooking;

public class ValDt extends BaseElement {

	@Override
	public String getElementName() {
		return "ValDt";
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
			// do nothing, value date is not used
			//booking.setValutaDate(content);
		}
	}

	@Override
	public void endElement(String qName) throws SAXException {
		if(context.isXmlState(XmlState.Ntry)){
			context.setContentElement(null);
		}
	}

}
