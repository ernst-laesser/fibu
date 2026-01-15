package ch.nc.asset.imp.ta.zkb;

import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Type extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;

		if (line.startsWith("Ihr Verkauf")) {
			transaction.type = "VERKAUF";
			found = true;
		} else if (line.startsWith("Ihr Kauf")) {
			transaction.type = "KAUF";
			found = true;
		}
		return found;
	}
}
