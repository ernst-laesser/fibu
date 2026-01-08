package ch.nc.fibu.imp.jour;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import ch.nc.fibu.imp.Booking;

public class JournalReader {

	private int iDate = -1;
	private int iBetrag = -1;
	private int iSoll = -1;
	private int iHaben = -1;

	final private String DATE = "DATUM";
	final private String SOLL = "SOLL";
	final private String HABEN = "HABEN";
	final private String BETRAG = "BETRAG";

	private CSVReader csvReader;
	private String[] record = null;
	private ArrayList<JournalBooking> journalBookingList = new ArrayList<JournalBooking>();
	private int selectedAccount;

	public JournalReader(String fileName) throws IOException {

		final CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
		this.csvReader = new CSVReaderBuilder(new FileReader(fileName)).withCSVParser(parser).build();

		// csvReader = new CSVReaderBuilder(new FileReader(fileName)).build();

		// search for heading line and get column indices
		while ((record = csvReader.readNext()) != null) {
			iDate = findIndex(record, DATE);
			iBetrag = findIndex(record, BETRAG);
			iSoll = findIndex(record, SOLL);
			iHaben = findIndex(record, HABEN);
			if (iDate >= 0 && iBetrag >= 0 && iSoll >= 0 && iHaben >= 0) {
				System.out.println("heading line and indices found");
				break;
			}
		}
		if (record == null) {
			System.out.println("Nothing usefull found");
		}
	}

	public void setSelectedAccount(int account) {
		this.selectedAccount = account;
	}

	public void read() throws IOException {
		JournalBooking journalBooking;
		int count = 0;
		int totalCount = 0;
		int errorCount = 0;

		while ((record = csvReader.readNext()) != null) {

			try {
				journalBooking = new JournalBooking();
				System.out.println(totalCount + ": Journal Date =" + record[iDate]);
				journalBooking.setDate(record[iDate]);
				journalBooking.setAmount(record[iBetrag]);
				journalBooking.setSoll(record[iSoll]);
				journalBooking.setHaben(record[iHaben]);
				if (selectedAccount == 0 || journalBooking.isRelevant(selectedAccount)) {
					journalBookingList.add(journalBooking);
					count++;
					;
				}
			} catch (Exception e) {
				System.out.println("failed on Journal Date =" + record[iDate] + "at total Count = "+ totalCount);
				errorCount++;
				e.printStackTrace();
			}
			totalCount++;

		}
		System.out.println(totalCount + " record processeD and " + count + " relevant records stored. " + errorCount +" errors encountered");

	}

	public boolean isDoubleBooking(Booking booking) {
		try {
			for (JournalBooking journalBooking : journalBookingList) {
				if (journalBooking.isDouble(booking)) {
					return true;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
