package ch.nc.asset;

public class Amount {
	public Double value;
	public Currency currency;
	
	public void setCurreny(String s) {
		currency=Currency.getCurrency(s);
	}
	public void setCurreny(Currency currency) {
		this.currency=currency;
	}
	
	public void setValue(String s) {
		value=Double.valueOf(s);
	}
}
