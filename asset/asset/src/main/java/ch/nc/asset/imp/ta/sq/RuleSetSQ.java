package ch.nc.asset.imp.ta.sq;

import java.util.ArrayList;

import ch.nc.asset.imp.ta.InputRuleBase;

public class RuleSetSQ {

	public ArrayList<InputRuleBase> getRules() {
		
		ArrayList<InputRuleBase> inputRules = new ArrayList<InputRuleBase>();

		inputRules.add(new IR_Isin());
		inputRules.add(new IR_Details());
		inputRules.add(new IR_Konto());
		inputRules.add(new IR_Reference());
		inputRules.add(new IR_ExpSq());
		inputRules.add(new IR_ExpExchange());
		inputRules.add(new IR_TaxCh());		
		inputRules.add(new IR_AccountValue());
		inputRules.add(new IR_Type());
		inputRules.add(new IR_Date());
		return inputRules;
	}

}
