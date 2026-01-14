package ch.nc.asset.imp.ta.sq;

import java.util.ArrayList;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Details extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Anzahl")&&line.contains("Preis")&&line.contains("Betrag")) {
			
			// next line contais the figures
			line = getLine(); // header line
			ArrayList<String> tokenList=Helper.getTokens(line);
			
			//transaction.tradeCurrency=Currency.getCurrency(tokenList.get(2));
			transaction.quantity=Integer.parseInt(tokenList.get(0));
			transaction.price=Helper.getDouble(tokenList.get(1));
			transaction.titleCurrency=Currency.getCurrency(tokenList.get(2));
			transaction.value=Helper.getDouble(tokenList.get(3));
			
			//line = (String) iterator.next();
			found = true;
		}
		return found;
	}
	
	

}
