package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Konto extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("IBAN:")) {
			transaction.accountIban=Helper.TextFollowing(line, "IBAN:");			
			found = true;
		}
		return found;
	}
	
	

}
