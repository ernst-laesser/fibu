package ch.nc.fibu.imp.camt53.elements;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class Dt extends BaseElement {

	@Override
	public String getElementName() {
		return "Dt";
	}

	@Override
	public void startElement(String qName, Attributes attributes) throws SAXException {

	}

	@Override
	public void content(String content) throws SAXException {
		BaseElement element = context.getContentElement();
		if (element!=null) {
			element.content(content);
		}
	}

	@Override
	public void endElement(String qName) throws SAXException {

	}

}
