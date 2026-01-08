package ch.nc.fibu.imp.mc;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import ch.nc.fibu.imp.BookingHandler;

public class Reader {

	private int iDate = -1;
	private int iBetrag = -1;
	private int iText = -1;

	final private String DATE = "Einkaufsdatum";
	final private String TEXT = "Buchungsdetails";
	final private String BETRAG = "Lastschrift in CHF";

	private CSVReader csvReader;
	private String[] record = null;

	public Reader(String fileName) throws IOException {

		final CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
		this.csvReader = new CSVReaderBuilder(new FileReader(fileName)).withSkipLines(1).withCSVParser(parser).build();

		// csvReader = new CSVReaderBuilder(new FileReader(fileName)).build();

		// search for heading line and get column indices
		while ((record = csvReader.readNext()) != null) {
			iDate = findIndex(record, DATE);
			iBetrag = findIndex(record, BETRAG);
			iText = findIndex(record, TEXT);
			if (iDate >= 0 && iBetrag >= 0 && iText >= 0) {
				System.out.println("heading line and indices found");
				break;
			}
		}
		if (record == null) {
			System.out.println("Nothing usefull found");
		}
	}

	public void read(BookingHandler bookingHandler) throws IOException {
		RawBooking rawBooking;
		int count = 0;

		while ((record = csvReader.readNext()) != null) {
			// skip empty lines and "Gutschrift"
			if (record.length >= iBetrag + 1 && record[iBetrag] != null && record[iBetrag].length() > 0) {
				
				rawBooking = new RawBooking();
				rawBooking.setDate(record[iDate]);
				rawBooking.setAmount(record[iBetrag].substring(1)); // remove leading "-"
				rawBooking.setText(record[iText]);

				bookingHandler.processBooking(rawBooking);
				count++;
			}
		}
		System.out.println(count + " records processed");

	}

	private int findIndex(String[] record, String fieldName) {
		for (int i = 0; i < record.length; i++) {
			if (fieldName.equals(record[i])) {
				return i;
			}
		}
		return -1;
	}

}
