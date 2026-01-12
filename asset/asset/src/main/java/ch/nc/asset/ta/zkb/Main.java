package ch.nc.asset.ta.zkb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
	//	Stream<String> lines = content.lines();
		/*
		 * Die Reihenfolge wie sie aus dem PDF extrahiert wird, hat keinen Zusammenhang zu
		 * der auf dem Papier. In einem ersten Durchlauf werden zuerst die relevanten Zeilen
		 * in spezifischen Strings zwischengespeichert. Diese werden im weiteren Verlauf analysiert
		 * und die relevanten Daten entnommen
		 * 
		 */

		//Iterator<String> iterator = lines.iterator();
		Lines lines=new Lines();
		lines.setIterator(content.lines().iterator());
		Transaction transaction=new Transaction();
		transaction.bank="ZKB";
		
		ArrayList <InputRuleBase> inputRules = new ArrayList <InputRuleBase>();
		
		inputRules.add(new IR_IsinAndDetails());
		inputRules.add(new IR_Konto());
		inputRules.add(new IR_Reference());
		inputRules.add(new IR_Sec());
		inputRules.add(new IR_TaxCh());
		inputRules.add(new IR_TradeTotal());
		inputRules.add(new IR_AccountValue());
		inputRules.add(new IR_Type());
		inputRules.add(new IR_Date());
		
		for(InputRuleBase rule: inputRules) {
			rule.setGetline(lines);
			rule.setTransaction(transaction);
		}
		
		
		while (lines.hasNext()) {
			try {
				String line = lines.next();
				boolean done;
				int i=0;
				InputRuleBase rule;
				while (i<inputRules.size()) {
					rule=inputRules.get(i);
					System.out.println("--Rule: " + rule.getClass().getSimpleName());
					done=rule.analyse(line);
					if(done) {
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
		
		System.out.println("-----Transaction-------------------------------------------");
		System.out.println(transaction.toString());
		
	}

}
