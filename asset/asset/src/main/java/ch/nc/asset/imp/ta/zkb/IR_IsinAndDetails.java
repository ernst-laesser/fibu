package ch.nc.asset.imp.ta.zkb;

import java.util.ArrayList;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_IsinAndDetails extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("ISIN")) {
			transaction.isin=Helper.TextFollowing(line, "ISIN");
			
			line = getLine(); // header line
			line = line + " "+ getLine(); // append value line
			ArrayList<String> tokenList=Helper.getTokens(line);
			transaction.titleCurrency=Currency.getCurrency(tokenList.get(1));
			transaction.tradeCurrency=Currency.getCurrency(tokenList.get(2));
			transaction.quantity=Integer.parseInt(tokenList.get(3));
			transaction.price=Helper.getDouble(tokenList.get(5));
			transaction.value=Helper.getDouble(tokenList.get(6));
			
			//line = (String) iterator.next();
			found = true;
		}
		return found;
	}
	
	

}
