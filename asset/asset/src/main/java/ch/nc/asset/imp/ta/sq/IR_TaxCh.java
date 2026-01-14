package ch.nc.asset.imp.ta.sq;

import java.util.ArrayList;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_TaxCh extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		if (line.contains("Abgabe (Eidg. Stempelsteuer)")) {
			ArrayList<String> tokenList=Helper.tokensAfterText (line, "Abgabe (Eidg. Stempelsteuer)");
			if (tokenList!=null) {
				transaction.taxCh=Helper.getDouble(tokenList.get(1));
				found = true;
			}		
			found = true;
		}
		return found;
	}
}
