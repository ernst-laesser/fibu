package ch.nc.asset.ta.zkb;

public class IR_Type extends InputRuleBase {

	public String getMessage() {
		return this.getClass().getSimpleName();
	}

	public boolean analyse(String line) {
		boolean found = false;
		
		if (line.startsWith("Ihr Verkauf")) {
			transaction.type="VERKAUF";			
			found = true;
		}
		return found;
	}
}
