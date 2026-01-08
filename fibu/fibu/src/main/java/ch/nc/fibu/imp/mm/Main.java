package ch.nc.fibu.imp.mm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
//import org.xml.sax.SAXException;

import ch.nc.fibu.imp.BookingWriter;
import ch.nc.fibu.imp.mm.item.Item;
import ch.nc.fibu.imp.mm.item.ItemHandler;
import ch.nc.fibu.imp.mm.receipt.Accounting;
import ch.nc.fibu.imp.mm.receipt.Receipt;

public class Main {

	public static void main(final String[] args) throws IOException, TikaException, Exception {

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		
		FileInputStream inputstream = new FileInputStream(
				new File("P:\\finanzen\\2025\\Migros\\20251001_20251031.pdf"));
		//FileInputStream inputstream = new FileInputStream(
		//		new File("P:\\finanzen\\2025\\Migros\\20250901_20250930.pdf"));
		
		// FileInputStream inputstream = new FileInputStream(new
		// File("P:\\finanzen\\2025\\Migros\\20250101_20250131.pdf"));
		// FileInputStream inputstream = new FileInputStream(new
		// File("P:\\finanzen\\2025\\Migros\\20250201_20250228.pdf"));
		// FileInputStream inputstream = new FileInputStream(new
		// File("P:\\finanzen\\2025\\Migros\\20250301_20250331.pdf"));
		// FileInputStream inputstream = new FileInputStream(new
		// File("P:\\finanzen\\2025\\Migros\\20250401_20250430.pdf"));
		//FileInputStream inputstream = new FileInputStream(
		//		new File("P:\\finanzen\\2025\\Migros\\20250501_20250531.pdf"));	
//		FileInputStream inputstream = new FileInputStream(
//				new File("P:\\finanzen\\2025\\Migros\\20250601_20250630.pdf"));
		
//		FileInputStream inputstream = new FileInputStream(
//				new File("P:\\finanzen\\2025\\Migros\\20250701_20250731.pdf"));
		
//		FileInputStream inputstream = new FileInputStream(
//				new File("P:\\finanzen\\2025\\Migros\\20250801_20250831.pdf"));
		
		ParseContext pcontext = new ParseContext();

		// parsing the document using PDF parser
		PDFParser pdfparser = new PDFParser();
		pdfparser.parse(inputstream, handler, metadata, pcontext);

		String content = handler.toString();
		System.out.println("Size of content: " + content.length());
		Stream<String> lines = content.lines();
		// System.out.println("Nr. of lines: " + lines.count());

		// Initiate processing System
		ItemHandler itemHandler = new ItemHandler();
		itemHandler.setInputFileName("P:\\finanzen\\2025\\Migros\\ItemsList-3.csv");
		itemHandler.setOutputFileName("P:\\finanzen\\2025\\Migros\\ItemsList-3.csv");
		itemHandler.read();
		Item.updateUsage = true;

		Accounting accounting = new Accounting();
		accounting.setItemHandler(itemHandler);

		BookingWriter bookingWriter = new BookingWriter();
		bookingWriter.openOutput("P:\\finanzen\\FIBU_CSV_IMPORT.csv");

		accounting.setBookingWriter(bookingWriter);

		Iterator<String> iterator = lines.iterator();
		int counter = 1;
		Receipt receipt;
		while (iterator.hasNext()) {
			try {
				String line = (String) iterator.next();
				if (line.contains("GENOSSENSCHAFT MIGROS")) {
					receipt = new Receipt();
					receipt.extractData(iterator);
					
					System.out.println(receipt.toString());
					accounting.processReceipt(receipt);
					counter++;
					
				}
			} catch (Exception e) {
				System.out.println("Problem in receipt nr. " + counter);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		itemHandler.write();
		bookingWriter.closeOutput();

		System.out.println("Anzahl Kassabons: " + counter);
	}
}