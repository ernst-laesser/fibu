package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Isin extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("ISIN:")) {
			transaction.isin=Helper.TextFollowing(line, "ISIN:");
			found = true;
		}
		return found;
	}
	
	

}
