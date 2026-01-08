package ch.nc.fibu.imp.camt53.elements;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class BaseElement {
	
	ProcessingContext context;
	
	public void setContext(ProcessingContext context) {
		this.context = context;
	}
	
	abstract public String getElementName();
	
	abstract public void startElement(String qName, Attributes attributes) throws SAXException;	
	abstract public void content(String content) throws SAXException;
	abstract public void endElement(String qName) throws SAXException;

}
