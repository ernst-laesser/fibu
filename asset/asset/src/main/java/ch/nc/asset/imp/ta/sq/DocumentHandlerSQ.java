package ch.nc.asset.imp.ta.sq;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import ch.nc.asset.imp.Lines;
import ch.nc.asset.imp.ta.InputRuleBase;
import ch.nc.asset.imp.ta.Transaction;

public class DocumentHandlerSQ {

	BodyContentHandler handler;
	Metadata metadata;
	ParseContext pcontext;
	PDFParser pdfparser;

	public DocumentHandlerSQ() {
		
	}

	public Transaction processDocument(String folder, String fileName, int year) throws IOException, SAXException, TikaException {

		String filePath = folder + "\\" + fileName;
		FileInputStream inputstream = new FileInputStream(new File(filePath));
		pdfparser = new PDFParser();
		metadata = new Metadata();
		pcontext=new ParseContext();
		pdfparser = new PDFParser();
		handler = new BodyContentHandler();
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
		lines.setSysOut(true);
		lines.setIterator(content.lines().iterator());
		
		Transaction transaction = new Transaction();
		transaction.bank = "SQ";
		transaction.year = year;
		transaction.fileName = fileName;

		ArrayList<InputRuleBase> inputRules = new ArrayList<InputRuleBase>();

		inputRules.add(new IR_Isin());
		inputRules.add(new IR_Details());
		inputRules.add(new IR_Konto());
		inputRules.add(new IR_Reference());
		inputRules.add(new IR_ExpSq());
		inputRules.add(new IR_ExpExchange());
		inputRules.add(new IR_TaxCh());		
		inputRules.add(new IR_AccountValue());
		inputRules.add(new IR_Type());
		inputRules.add(new IR_Date());
		

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
					//System.out.println("--Rule: " + rule.getClass().getSimpleName());
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
