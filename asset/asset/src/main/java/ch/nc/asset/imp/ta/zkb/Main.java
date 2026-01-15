package ch.nc.asset.imp.ta.zkb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import ch.nc.asset.imp.ta.DocumentHandler;
import ch.nc.asset.imp.ta.Transaction;
import ch.nc.asset.imp.ta.TransactionWriter;


public class Main {

	public static void main(final String[] args) throws IOException, TikaException, Exception {
		
		DocumentHandler documentHandler=new DocumentHandler();
		String folder="C:\\Users\\ernst\\SecureSafe\\Privater Safe\\Finanzen\\Fibu_2025\\ZKB\\9-4700-00553564 Anlageportfolio\\Transaktionen";
		Path path = Paths.get(folder);
		
		TransactionWriter writer = new TransactionWriter();
		writer.openOutput("C:\\Java\\git\\repository\\asset\\asset\\src\\main\\resources\\transactions.csv");

        try (Stream<Path> paths = Files.list(path)) {
            System.out.println("Inhalt von: " + path.toAbsolutePath());
            paths.forEach(p -> {
            	Transaction transaction=null;
                if (Files.isDirectory(p)) {
                    System.out.println("  [Ordner] " + p.getFileName());
                } else {
                    String fileName =p.getFileName().toString();
                    System.out.println("  [Datei]  " + p.getFileName());
                    try {
						transaction = documentHandler.processDocument(p, fileName);
					} catch (IOException | SAXException | TikaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		writer.addTransaction(transaction);
                }
            });
        writer.closeOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
	}

}
