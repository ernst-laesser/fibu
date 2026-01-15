package ch.nc.asset.imp.ta.sq;

import java.util.ArrayList;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_ExpExchange extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		ArrayList<String> tokenList=Helper.tokensAfterText (line, "Börsengebühren und sonstige Spesen");
		if (tokenList!=null) {
			transaction.expenses+=Helper.getDouble(tokenList.get(1));			
			found = true;
		}
		return found;
	}
}
