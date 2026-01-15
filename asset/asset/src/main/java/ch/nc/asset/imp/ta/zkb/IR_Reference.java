package ch.nc.asset.imp.ta.zkb;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Reference extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Abwicklungs-Nr.")) {
			transaction.reference=Helper.TextFollowing(line, "Abwicklungs-Nr.");			
			found = true;
		}
		return found;
	}
	
	

}
