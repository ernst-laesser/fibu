package ch.nc.fibu.imp.camt53.elements;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {

	ProcessingContext context;
	HashMap<String, BaseElement> elements;

	public XmlHandler(ProcessingContext context) {
		this.context = context;
		elements = new HashMap<String, BaseElement>(20);

		addElement(new Ntry());
		addElement(new Amt());
		addElement(new BookgDt());
		addElement(new ValDt());
		addElement(new Dt());
		addElement(new CdtDbtInd());
		addElement(new NtryDtls());
		addElement(new AddtlNtryInf());
	}

	private void addElement(BaseElement element) {
		element.setContext(context);
		elements.put(element.getElementName(), element);
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		BaseElement element = elements.get(qName);
		if (element != null) {
			element.startElement(qName, attributes);
			//System.out.println("Processed startElement for qName: " + qName + ", xmlState=" + context.getXmlState());
		} else {
			//System.out.println("startElement, element not found for qName=" + qName);
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		BaseElement element = elements.get(qName);
		if (element != null) {
			element.endElement(qName);
			//System.out.println("Processed endElement for qName: " + qName + ", xmlState=" + context.getXmlState());
		} else {
			//System.out.println("endElement, element not found for qName=" + qName);
		}
		
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		BaseElement element = context.getContentElement();
		if (element!= null) {
			String content = new String(ch, start, length);
			element.content(content);
			//System.out.println("Processed content for element: " + element.getElementName() + " with content=" + content);
		} else {
			//System.out.println("No element found for conent processing");
		}
		
	}
}
