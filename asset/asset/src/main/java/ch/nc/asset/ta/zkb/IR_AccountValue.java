package ch.nc.asset.ta.zkb;

import java.util.ArrayList;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_AccountValue extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		ArrayList<String> tokenList=tokensAfterText (line, "Total zu Ihren Gunsten Valuta");
		if (tokenList!=null) {
			transaction.accountValue=Helper.getDouble(tokenList.get(1));			
			found = true;
		} else {
			tokenList=tokensAfterText (line, "Total zu Ihren Lasten Valuta");
			if (tokenList!=null) {
				transaction.accountValue=Helper.getDouble(tokenList.get(1));			
				found = true;
			}
		}
		return found;
	}
	
	private ArrayList<String> tokensAfterText (String source, String text){
		int i = source.indexOf(text);
		if (i>=0) {
			String s =source.substring(i+text.length());
			ArrayList<String> tokenList=Helper.getTokens(s);
			return tokenList;
		} else {
			return null;
		}
	}
}
