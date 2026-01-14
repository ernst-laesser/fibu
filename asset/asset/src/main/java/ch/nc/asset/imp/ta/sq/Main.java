package ch.nc.asset.imp.ta.sq;

import java.io.IOException;

import org.apache.tika.exception.TikaException;

import ch.nc.asset.imp.ta.Transaction;


public class Main {

	public static void main(final String[] args) throws IOException, TikaException, Exception {

		DocumentHandler documentHandler = new DocumentHandler();
		String folder = "C:\\Users\\ernst\\SecureSafe\\Privater Safe\\Finanzen\\Fibu_2025\\SwissQuote\\Transactions\\";
		;
		String fileName = "Borsenabrechnung_294034_880522464_20250703.pdf";

		/*
		 * Die Reihenfolge wie sie aus dem PDF extrahiert wird, hat keinen Zusammenhang
		 * zu der auf dem Papier. In einem ersten Durchlauf werden zuerst die relevanten
		 * Zeilen in spezifischen Strings zwischengespeichert. Diese werden im weiteren
		 * Verlauf analysiert und die relevanten Daten entnommen
		 * 
		 */
		try {
			Transaction transaction = documentHandler.processDocument(folder, fileName, 2025);
			System.out.println(transaction.toString());

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * itemHandler.write(); bookingWriter.closeOutput();
	 * 
	 * System.out.println("Anzahl Kassabons: " + counter);
	 */
}
