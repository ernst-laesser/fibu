package ch.nc.asset.imp.ta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import ch.nc.asset.imp.ta.sq.RuleSetSQ;
import ch.nc.asset.imp.ta.zkb.RuleSetZKB;

public class Main {

	public static void main(String[] args) {

//		String folder="C:\\Users\\ernst\\SecureSafe\\Privater Safe\\Finanzen\\Fibu_2025\\ZKB\\9-4700-00553564 Anlageportfolio\\Transaktionen";
		Path path = Paths.get(args[2]);
//		DocumentHandler documentHandler = new DocumentHandler();
//		documentHandler.setBank(args[0]);
//		documentHandler.setYear(Integer.parseInt(args[1]));
//		if (args[0].equals("ZKB")) {
//
//			documentHandler.setInputRules(new RuleSetZKB().getRules());
//		} else if (args[0].equals("SQ")) {
//			documentHandler.setInputRules(new RuleSetSQ().getRules());
//		}
		
		TransactionWriter writer = new TransactionWriter();
		writer.openOutput("C:\\Java\\git\\repository\\asset\\asset\\src\\main\\resources\\transactions.csv");
		if (Files.isDirectory(path)) {
			try (Stream<Path> paths = Files.list(path)) {
				System.out.println("Inhalt von: " + path.toAbsolutePath());
				paths.forEach(p -> {
					Transaction transaction = null;
					if (Files.isDirectory(p)) {
						System.out.println("  [Ordner] " + p.getFileName());
					} else {
						String fileName = p.getFileName().toString();
						System.out.println("  [Datei]  " + p.getFileName());
						try {
							DocumentHandler documentHandler = new DocumentHandler();
							documentHandler.setBank(args[0]);
							documentHandler.setYear(Integer.parseInt(args[1]));
							if (args[0].equals("ZKB")) {

								documentHandler.setInputRules(new RuleSetZKB().getRules());
							} else if (args[0].equals("SQ")) {
								documentHandler.setInputRules(new RuleSetSQ().getRules());
							}
							transaction = documentHandler.processDocument(p, fileName);
						} catch (IOException | SAXException | TikaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(transaction.toString());
						writer.addTransaction(transaction);
					}
				});
				writer.closeOutput();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//			String folder=path.toAbsolutePath().toString();
			String fileName = path.getFileName().toString();
			Transaction transaction=null;
			try {
				DocumentHandler documentHandler = new DocumentHandler();
				documentHandler.setBank(args[0]);
				documentHandler.setYear(Integer.parseInt(args[1]));
				if (args[0].equals("ZKB")) {

					documentHandler.setInputRules(new RuleSetZKB().getRules());
				} else if (args[0].equals("SQ")) {
					documentHandler.setInputRules(new RuleSetSQ().getRules());
				}
				transaction = documentHandler.processDocument(path, fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(transaction.toString());
		}

	}

}
