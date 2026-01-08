package ch.nc.fibu.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.opencsv.CSVWriter;

import ch.nc.fibu.imp.jour.JournalReader;

public class BookingWriter {

	private CSVWriter writer;
	private JournalReader journal=null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private int counter=0;

	/*
	 * public static void writeDataLineByLine(String filePath) { // first create
	 * file object for file placed at location // specified by filepath File file =
	 * new File(filePath); try { // create FileWriter object with file as parameter
	 * FileWriter outputfile = new FileWriter(file);
	 * 
	 * // create CSVWriter object filewriter object as parameter CSVWriter writer =
	 * new CSVWriter(outputfile);
	 * 
	 * // adding header to csv String[] header = { "DATUM", "SOLL", "HABEN",
	 * "BETRAG", "TEXT" }; writer.writeNext(header);
	 * 
	 * // add data to csv String[] data1 = { "Aman", "10", "620" };
	 * writer.writeNext(data1); String[] data2 = { "Suraj", "10", "630" };
	 * writer.writeNext(data2);
	 * 
	 * // closing writer connection writer.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	public void setJournalReader(JournalReader journal) {
		this.journal = journal;
	}

	public void openOutput(String filePath) {
		// first create file object for file placed at location
		// specified by filepath
		File file = new File(filePath);
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);

			// adding header to csv
			String[] header = { "DATUM", "SOLL", "HABEN", "BETRAG", "TEXT", "TEMP1" };
			writer.writeNext(header);
			++counter;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addBooking(Booking booking) {
		boolean doubleBooking = false;
		if (journal!=null) {
			doubleBooking = journal.isDoubleBooking(booking);
			booking.setDouble(doubleBooking);
		}
		String amountString = String.format("%.2f", booking.getBetrag());
		String dateString = dateFormat.format(booking.getDate());
		
		String[] data = { dateString, booking.getSoll()+"", booking.getHaben()+"", amountString,
				booking.getText(), booking.getTemp() };
		writer.writeNext(data);
		++counter;
	}

	public void closeOutput() throws IOException {
		// closing writer connection
		writer.close();
		System.out.println(counter + " records processe added (including header");
	}
}