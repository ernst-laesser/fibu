package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;


public class IR_Date extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Gland, ")) {
			transaction.date=Helper.getDateFollowing(line, "Gland,");			
			found = true;
		}
		return found;
	}
}
