package ch.nc.asset.ta.zkb;

import java.util.Iterator;

import ch.nc.asset.Context;
import ch.nc.asset.imp.ta.Transaction;

public abstract class InputRuleBase {

	Iterator<String> iterator;
	Transaction transaction;
	Context context;

	public void setIterator(Iterator<String> iterator) {
		this.iterator = iterator;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	abstract public String getMessage();

}
