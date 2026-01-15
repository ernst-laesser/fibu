package ch.nc.asset.imp.ta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import ch.nc.asset.imp.Lines;

public class DocumentHandler {
	
	private String bank;
	private int year;
	ArrayList<InputRuleBase> inputRules;	

	public DocumentHandler() {
		
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setInputRules(ArrayList<InputRuleBase> inputRules) {
		this.inputRules = inputRules;
	}

	public Transaction processDocument(Path path, String fileName) throws IOException, SAXException, TikaException {

//		String filePath = folder + "\\" + fileName;
		FileInputStream inputstream = new FileInputStream(path.toFile());
		Metadata metadata = new Metadata();
		ParseContext pcontext=new ParseContext();
		PDFParser pdfparser = new PDFParser();
		BodyContentHandler handler = new BodyContentHandler();
		pdfparser.parse(inputstream, handler, metadata, pcontext);

		String content = handler.toString();
		// System.out.println("Size of content: " + content.length());
		// Stream<String> lines = content.lines();
		/*
		 * Die Reihenfolge wie sie aus dem PDF extrahiert wird, hat keinen Zusammenhang
		 * zu der auf dem Papier. In einem ersten Durchlauf werden zuerst die relevanten
		 * Zeilen in spezifischen Strings zwischengespeichert. Diese werden im weiteren
		 * Verlauf analysiert und die relevanten Daten entnommen
		 * 
		 */

		// Iterator<String> iterator = lines.iterator();
		Lines lines = new Lines();
		lines.setSysOut(false);
		lines.setIterator(content.lines().iterator());
		
		Transaction transaction = new Transaction();
		transaction.bank = bank;
		transaction.year = year;
		transaction.fileName = fileName;	

		for (InputRuleBase rule : inputRules) {
			rule.setGetline(lines);
			rule.setTransaction(transaction);
		}

		while (lines.hasNext()) {
			try {
				String line = lines.next();
				boolean done;
				int i = 0;
				InputRuleBase rule;
				while (i < inputRules.size()) {
					rule = inputRules.get(i);
//					System.out.println("--Rule: " + rule.getClass().getSimpleName());
					done = rule.analyse(line);
					if (done) {
						// remove the rule which has completed.
						// do not increment the index!
						inputRules.remove(i);
					} else {
						i++;
					}

				}

			} catch (Exception e) {
				System.out.println("Problem in line nr. " + lines.getCounter());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return transaction;

	}
}
