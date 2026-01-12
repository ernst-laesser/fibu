package ch.nc.asset.ta.zkb;

import java.util.ArrayList;

import ch.nc.asset.Helper;

public class IR_AccountValue extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		int i = line.indexOf("Total zu Ihren Gunsten Valuta");
		if (i>=0) {
			String s =line.substring(i+"Total zu Ihren Gunsten Valuta".length());
			ArrayList<String> tokenList=Helper.getTokens(s);
			//transaction.titleCurrency=Currency.getCurrency(tokenList.get(0));
			transaction.accountValue=Helper.getDouble(tokenList.get(1));			
			found = true;
		}
		return found;
	}
}
