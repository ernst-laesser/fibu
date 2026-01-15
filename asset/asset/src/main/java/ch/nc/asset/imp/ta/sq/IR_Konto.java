package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Konto extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("auf Kontonummer")) {
			transaction.accountIban=Helper.TextFollowing(line, "auf Kontonummer");			
			found = true;
		}else if (line.contains("Betrag gutgeschrieben auf Ihrer Kontonummer")) {
			transaction.accountIban=Helper.TextFollowing(line, "Betrag gutgeschrieben auf Ihrer Kontonummer");
			found=true;
		}
		return found;
	}
	
	

}
