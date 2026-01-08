package ch.nc.fibu.imp.mm.item;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class ItemHandler {

	static public SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


	private int iDate = 0;
	private int iName = 1;
	private int iAccount = 2;
	private int iUsage = 3;
	
	private String inputFileName;
	private String outputFileName;
	private Date date;
	
	private ItemList itemList = new ItemList();

	private CSVReader csvReader;
	private String[] record = null;

	private CSVWriter writer;
	
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void setDate(Date date) {
			this.date =date;
	}

	public ItemHandler() {
	}

	public void read() throws IOException {

		final CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
		this.csvReader = new CSVReaderBuilder(new FileReader(inputFileName)).withSkipLines(1).withCSVParser(parser)
				.build();
		
		//header is skipped by "withSiplLines(1)!
		//record = csvReader.readNext();

		Item item;
		int count = 0;

		while ((record = csvReader.readNext()) != null) {
			item = new Item();
			try {
				item.setDate(dateFormat.parse(record[iDate]));

				item.setName(record[iName]);
				item.setAccount(Integer.parseInt(record[iAccount]));
				item.setUsage(Integer.parseInt(record[iUsage]));

				itemList.add(item);
				count++;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(count + " items read");
		System.out.println(itemList.toString());
		this.csvReader.close();
	}

	public void write() throws IOException {
		
		System.out.println(itemList.toString());
		
		FileWriter outputfile = new FileWriter(outputFileName);
		// create CSVWriter object filewriter object as parameter
		writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				CSVWriter.DEFAULT_LINE_END);
		// adding header to csv
		String[] header = { "DATE", "NAME", "ACCOUNT", "USAGE" };
		writer.writeNext(header);
		String dateString;
		Iterator<Item> iterator = itemList.iterator();
		Item item;
		while (iterator.hasNext()) {
			item = iterator.next();
			dateString = dateFormat.format(item.getDate());
			String[] data = { dateString, item.getName(), Integer.toString(item.getAccount()),
					Integer.toString(item.getUsage()) };
			writer.writeNext(data);
		}
		writer.close();
		System.out.println(itemList.toString());
	}
	
	public Integer getAccout (String articleName) {
		articleName=articleName.trim();
		Item item = itemList.getItem(articleName);
		if(item == null) {
			item = new Item();
			item.setDate(date);
			item.setName(articleName);
			item.setAccount(0);
			item.setUsage(0);
			itemList.add(item);
			return null;
		} else {
			item.updateUsage();
			return item.getAccount();	
		}
	}

}
