package ch.nc.asset.imp.ta.zkb;

import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Sec extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("SEC Fee (USA)")) {
			transaction.expenses+=Helper.DoubleFollowing(line, "SEC Fee (USA)");			
			found = true;
		}
		return found;
	}
}
