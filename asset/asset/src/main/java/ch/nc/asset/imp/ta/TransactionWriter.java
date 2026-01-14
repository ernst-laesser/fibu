package ch.nc.asset.imp.ta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.opencsv.CSVWriter;

public class TransactionWriter {

	private CSVWriter writer;
	// private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private int counter = 0;

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
			Transaction transaction = new Transaction();
			String[] header = transaction.getHeader();
			writer.writeNext(header);
			++counter;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addTransaction(Transaction transaction) {

		String[] data = transaction.getValues();
		writer.writeNext(data);
		++counter;
	}

	public void closeOutput() throws IOException {
		// closing writer connection
		writer.close();
		System.out.println(counter + " records processe added (including header");
	}
}