package ch.nc.asset.ta.zkb;

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

import ch.nc.asset.imp.ta.Transaction;


public class Main {

	public static void main(final String[] args) throws IOException, TikaException, Exception {

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		
		FileInputStream inputstream = new FileInputStream(
				new File("C:\\Users\\ernst\\SecureSafe\\Privater Safe\\Finanzen\\Fibu_2025\\ZKB\\9-4700-00553564 Anlageportfolio\\Transaktionen\\250312_Kundenabrechnung Borse Wertschriftendepot 1-2600-01314953_1743585187909_Lasser Ernst.pdf"));
		
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
		Transaction transaction=new Transaction();
		IR_IsinAndDetails ir_IsinAndDetails=new IR_IsinAndDetails();
		ir_IsinAndDetails.setIterator(iterator);
		ir_IsinAndDetails.setTransaction(transaction);
		
		//Receipt receipt;
		String reference = null;
		String details = null;
		String kommissionSQ = null;
		String date=null;
		String title=null;
		String totalAmount=null;
		while (iterator.hasNext()) {
			try {
				String line = (String) iterator.next();
				System.out.println(counter++ +": "+line);
				if (line.contains("Abwicklungs-Nr.")) {
					reference=line;					
				}
				if (line.contains("Eidg. Abgaben")) {
					kommissionSQ=line;					
				}
				if (line.contains("Anzahl")) {
					line = (String) iterator.next();
					details=line;					
				}
				ir_IsinAndDetails.analyse(line);
				if (line.contains("ISIN")) {
					title=line;
					line = (String) iterator.next();
					details=line;
					line = (String) iterator.next();
					details = details +" "+line;
				}
				if (line.contains("Buchungstag:")) {
					date=line;					
				}
				if (line.contains("Total")) {
					totalAmount=line;					
				}
			} catch (Exception e) {
				System.out.println("Problem in receipt nr. " + counter);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("transaction: "+reference);
		System.out.println("details: "+details);
		System.out.println("kommissionSQ: "+kommissionSQ);
		System.out.println("date: "+date);
		System.out.println("title: "+title);
		System.out.println("totalAmount: "+totalAmount);
		System.out.println("------------------------------------------------");
		System.out.println(transaction.toString());
		/*
		 * itemHandler.write(); bookingWriter.closeOutput();
		 * 
		 * System.out.println("Anzahl Kassabons: " + counter);
		 */
	}

}
