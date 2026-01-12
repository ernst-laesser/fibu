package ch.nc.asset.ta.zkb;

import ch.nc.asset.Helper;


public class IR_TradeTotal extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.length()<20 && line.startsWith("Total")) {
			transaction.tradeTotal=Helper.DoubleFollowing(line, "Total");			
			found = true;
		}
		return found;
	}
}
