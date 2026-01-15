package ch.nc.asset.imp.ta.zkb;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Konto extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Konto-Nr.")) {
			transaction.accountIban=Helper.TextFollowing(line, "Konto-Nr.");			
			found = true;
		}
		return found;
	}
	
	

}
