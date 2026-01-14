package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Reference extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Unsere Referenz:")) {
			transaction.reference=Helper.TextFollowing(line, "Unsere Referenz:");			
			found = true;
		}
		return found;
	}
	
	

}
