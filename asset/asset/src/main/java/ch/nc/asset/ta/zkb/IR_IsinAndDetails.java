package ch.nc.asset.ta.zkb;

import java.util.ArrayList;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;

public class IR_IsinAndDetails extends InputRuleBase {

	private String isin;
	private String details;
	
	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("ISIN")) {
			transaction.isin=Helper.TextFollowing(line, "ISIN");
			
			line = (String) iterator.next();
			details = line;
			ArrayList<String> tokenList=Helper.getTokens(line);
			transaction.titleCurrency=Currency.getCurrency(tokenList.get(1));
			transaction.tradeCurrency=Currency.getCurrency(tokenList.get(2));
			transaction.quantity=Integer.parseInt(tokenList.get(3));
			transaction.price=Double.parseDouble(tokenList.get(5));
			transaction.value=Double.parseDouble(tokenList.get(6));
			
			//line = (String) iterator.next();
			found = true;
		}
		return found;
	}

}
