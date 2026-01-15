package ch.nc.asset.imp.ta.zkb;

import java.util.ArrayList;

import ch.nc.asset.imp.ta.InputRuleBase;

public class RuleSetZKB {
	
	public ArrayList<InputRuleBase> getRules() {
		ArrayList<InputRuleBase> inputRules = new ArrayList<InputRuleBase>();

		inputRules.add(new IR_IsinAndDetails());
		inputRules.add(new IR_Konto());
		inputRules.add(new IR_Reference());
		inputRules.add(new IR_Sec());
		inputRules.add(new IR_TaxCh());
		inputRules.add(new IR_TradeTotal());
		inputRules.add(new IR_AccountValue());
		inputRules.add(new IR_Type());
		inputRules.add(new IR_Date());
		return inputRules;
	}

}
