package ch.nc.asset.imp.ta.zkb;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_TaxCh extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Eidg. Abgaben")) {
			transaction.taxCh=Helper.DoubleFollowing(line, "Eidg. Abgaben");			
			found = true;
		}
		return found;
	}
}
