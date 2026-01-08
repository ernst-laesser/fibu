package ch.nc.fibu.imp.mc;

import ch.nc.fibu.imp.BookingHandler;
import ch.nc.fibu.imp.BookingWriter;
import ch.nc.fibu.imp.jour.JournalReader;
import ch.nc.fibu.imp.mc.bookingRules.BR_Apple;
import ch.nc.fibu.imp.mc.bookingRules.BR_Default;
import ch.nc.fibu.imp.mc.bookingRules.BR_MigrosRestWetzikon;
import ch.nc.fibu.imp.mc.bookingRules.BR_MigrosWetzikon;
import ch.nc.fibu.imp.mc.bookingRules.BR_RestRiedFeusisberg;
import ch.nc.fibu.imp.mc.bookingRules.BR_TagiAbo;
import ch.nc.fibu.imp.mc.bookingRules.BR_YouTube;

public class Main {

	public static void main(String[] args) {

		try {
			// File inputFile = new File("P:\\finanzen\\2025\\PostFinance\\CHF
			// 85-470602-0\\camt.053_P_CH3909000000854706020_1115583950_0_2025020102101633.xml");
			// File outputFile new File("P:\\finanzen\\PF_import.csv");

			JournalReader journal = new JournalReader("P:\\finanzen\\FIBJOUR.csv");
			journal.setSelectedAccount(2010);
			journal.read();

			Reader reader = new Reader(
					"P:\\finanzen\\2025\\PostFinance\\Mastercard\\export_kreditkartenuebersicht_XXXXXXXXXXXX4152_20251214.csv");

			BookingWriter bookingWriter = new BookingWriter();
			bookingWriter.openOutput("P:\\finanzen\\FIBU_CSV_IMPORT.csv");
			bookingWriter.setJournalReader(journal);

			BookingHandler bookingHandler = new BookingHandler(bookingWriter);
			bookingHandler.addBookingRule(new BR_Apple());
			bookingHandler.addBookingRule(new BR_MigrosRestWetzikon());
			bookingHandler.addBookingRule(new BR_MigrosWetzikon());
			bookingHandler.addBookingRule(new BR_RestRiedFeusisberg());
			bookingHandler.addBookingRule(new BR_YouTube());
			bookingHandler.addBookingRule(new BR_TagiAbo());

			bookingHandler.addBookingRule(new BR_Default());

			reader.read(bookingHandler);

			bookingWriter.closeOutput();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
