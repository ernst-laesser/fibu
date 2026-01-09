package ch.nc.asset.imp.ta;

import java.lang.reflect.Field;
import java.util.Date;

import ch.nc.asset.Currency;

public class Transaction {
	private static final Class<?> String = null;
	public String bank;
	public String reference;
	public String type;
	public Date date;
	public String isin;
	public Currency titleCurrency;
	public int quantity;
	public double price;
	public double value;
	public double taxCh;
	public double expenseExchange;
	public double expenseBank;
	public Currency tradeCurrency;
	public double tradeTotal;
	public String accountIban;
	public Currency accountCurrency;
	public double accountValue;
	
	public String toString() {
		Field[] myFields = this.getClass().getFields();
		String name;
		String value;
		String type;
		StringBuffer sb = new StringBuffer();
		
		for(Field field: myFields) {
			name=field.getName();
			value=null;
			try {
				type=field.getType().getName();
				if(type=="java.lang.String") {
					value=(String)field.get(this);
				}else if(type=="int") {
					value=Integer.toString(field.getInt(this));
				}else {
					value=type;
				}
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				value=null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(name+": "+value+"\n");
		}
		return sb.toString();
	}
	

}
