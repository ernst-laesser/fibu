package ch.nc.asset.ta.zkb;

import ch.nc.asset.Helper;


public class IR_Date extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Buchungstag:")) {
			transaction.date=Helper.getDateFollowing(line, "Buchungstag:");			
			found = true;
		}
		return found;
	}
}
