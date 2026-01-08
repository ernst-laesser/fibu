package ch.nc.fibu.imp.ca.sq;

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

public class Main {

	public static void main(final String[] args) throws IOException, TikaException, Exception {
		
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		
		FileInputStream inputstream = new FileInputStream(
				new File("C:\\Users\\ernst\\SecureSafe\\Privater Safe\\Finanzen\\Fibu_2025\\SwissQuote\\CoporateActions\\Corporate-Action-Abrechnung_294034_775144190_20250203.pdf"));
		
		ParseContext pcontext = new ParseContext();

		// parsing the document using PDF parser
		PDFParser pdfparser = new PDFParser();
		pdfparser.parse(inputstream, handler, metadata, pcontext);

		String content = handler.toString();
		System.out.println("Size of content: " + content.length());
		Stream<String> lines = content.lines();
		/*
		 * Die Reihenfolge wie sie aus dem PDF extrahiert wird, hat keinen Zusammenhang zu
		 * der auf dem Papier. In einem ersten Durchlauf werden zuerst die relevanten Zeilen
		 * in spezifischen Strings zwischengespeichert. Diese werden im weiteren Verlauf analysiert
		 * und die relevanten Daten entnommen
		 * 
		 */
		//System.out.println("Nr. of lines: " + lines.count());

		// Initiate processing System
		/*
		 * ItemHandler itemHandler = new ItemHandler();
		 * itemHandler.setInputFileName("P:\\finanzen\\2025\\Migros\\ItemsList-3.csv");
		 * itemHandler.setOutputFileName("P:\\finanzen\\2025\\Migros\\ItemsList-3.csv");
		 * itemHandler.read(); Item.updateUsage = true;
		 * 
		 * Accounting accounting = new Accounting();
		 * accounting.setItemHandler(itemHandler);
		 * 
		 * BookingWriter bookingWriter = new BookingWriter();
		 * bookingWriter.openOutput("P:\\finanzen\\FIBU_CSV_IMPORT.csv");
		 * 
		 * accounting.setBookingWriter(bookingWriter);
		 */

		Iterator<String> iterator = lines.iterator();
		int counter = 1;
		//Receipt receipt;
		String transaction = null;
		String details = null;
		String kommissionSQ = null;
		String date=null;
		String title=null;
		String totalAmount=null;
		while (iterator.hasNext()) {
			try {
				String line = (String) iterator.next();
				System.out.println(counter++ +": "+line);
				if (line.contains("Unsere Referenz:")) {
					transaction=line;					
				}
				if (line.contains("Kommission Swissquote Bank AG")) {
					kommissionSQ=line;					
				}
				if (line.contains("Anzahl")) {
					line = (String) iterator.next();
					details=line;					
				}
				if (line.contains("Titel")) {
					line = (String) iterator.next();
					title=line;					
				}
				if (line.contains("Valutadatum")) {
					date=line;					
				}
				if (line.contains("Zu Ihren Lasten")) {
					totalAmount=line;					
				}
			} catch (Exception e) {
				System.out.println("Problem in receipt nr. " + counter);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("transaction: "+transaction);
		System.out.println("details: "+details);
		System.out.println("kommissionSQ: "+kommissionSQ);
		System.out.println("date: "+date);
		System.out.println("title: "+title);
		System.out.println("totalAmount: "+totalAmount);
		/*
		 * itemHandler.write(); bookingWriter.closeOutput();
		 * 
		 * System.out.println("Anzahl Kassabons: " + counter);
		 */
	}

}
