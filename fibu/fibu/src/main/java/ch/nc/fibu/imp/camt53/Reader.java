package ch.nc.fibu.imp.camt53;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ch.nc.fibu.imp.BookingHandler;
import ch.nc.fibu.imp.camt53.elements.ProcessingContext;
import ch.nc.fibu.imp.camt53.elements.XmlHandler;



public class Reader {
	
	private File inputFile;
	
	public Reader(String fileName) throws IOException {
		inputFile = new File(fileName);
	}
	
	public void read(BookingHandler bookingHandler) throws Exception {
		ProcessingContext context=new ProcessingContext();
        context.setBookingHandler(bookingHandler);
        XmlHandler xmlHandler = new XmlHandler(context);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(inputFile, xmlHandler);
	}

}
