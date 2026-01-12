package ch.nc.asset.ta.zkb;

import ch.nc.asset.Context;
import ch.nc.asset.imp.ta.Transaction;

public abstract class InputRuleBase {
	
	Lines lines;
	Transaction transaction;
	Context context;

	public void setGetline(Lines lines) {
		this.lines = lines;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	public String getLine(){
		return this.lines.next();
	}


	abstract public String getMessage();
	abstract public boolean analyse(String line);

}
