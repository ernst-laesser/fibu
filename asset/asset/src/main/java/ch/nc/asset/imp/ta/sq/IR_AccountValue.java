package ch.nc.asset.imp.ta.sq;

import java.util.ArrayList;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;
import ch.nc.asset.imp.ta.InputRuleBase;

public class IR_AccountValue extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		ArrayList<String> tokenList=Helper.tokensAfterText (line, "Zu Ihren Gunsten");
		if (tokenList!=null) {
			transaction.accountCurrency=Currency.getCurrency(tokenList.get(0));
			transaction.accountValue=Helper.getDouble(tokenList.get(1));			
			found = true;
		} else {
			tokenList=Helper.tokensAfterText (line, "Zu Ihren Lasten");
			if (tokenList!=null) {
				transaction.accountCurrency=Currency.getCurrency(tokenList.get(0));				
				transaction.accountValue=Helper.getDouble(tokenList.get(1));
				found = true;
			}
		}
		return found;
	}
	
}
