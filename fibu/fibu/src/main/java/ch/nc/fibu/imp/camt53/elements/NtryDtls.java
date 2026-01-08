package ch.nc.fibu.imp.camt53.elements;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class NtryDtls extends BaseElement {

	@Override
	public String getElementName() {
		return "NtryDtls";
	}

	@Override
	public void startElement(String qName, Attributes attributes) throws SAXException {
		context.setXmlState(XmlState.NtryDtls);
		
	}

	@Override
	public void content(String content) throws SAXException {
	}

	@Override
	public void endElement(String qName) throws SAXException {
		context.setXmlState(XmlState.Ntry);		
	}

}
