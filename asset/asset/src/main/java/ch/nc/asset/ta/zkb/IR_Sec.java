package ch.nc.asset.ta.zkb;

import ch.nc.asset.Helper;

public class IR_Sec extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("SEC Fee (USA)")) {
			transaction.expenseExchange=Helper.DoubleFollowing(line, "SEC Fee (USA)");			
			found = true;
		}
		return found;
	}
}
