package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_Type extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;

		if ((line.contains("Transaktion:")||line.contains("Börsentransaktion:")) && line.contains("Verkauf")) {
			transaction.type = "VERKAUF";
			found = true;
		} else if ((line.contains("Transaktion:")||line.contains("Börsentransaktion:")) && line.contains("Kauf")) {
			transaction.type = "KAUF";
			found = true;
		}
		return found;
	}
}
