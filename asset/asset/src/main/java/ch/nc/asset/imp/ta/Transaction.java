package ch.nc.asset.imp.ta;

import java.lang.reflect.Field;
import java.util.Date;

import ch.nc.asset.Currency;
import ch.nc.asset.Helper;

public class Transaction {
	
	public String bank;
	public int year;
	public String reference;
	public String type;
	public Date date;
	public String isin;
	public Currency titleCurrency;
	public int quantity;
	public double price;
	public double value;
	public double taxCh;
	public double expenses;
	public Currency tradeCurrency;
	public double tradeTotal;
	public String accountIban;
	public Currency accountCurrency;
	public double accountValue;
	
	public String fileName;
	
	public String[] getHeader() {
		Field[] myFields = this.getClass().getFields();
		int length = myFields.length;
		String[] header=new String[length];
		for (int i=0; i<length;i++) {
			header[i]=myFields[i].getName();
		}
		System.out.println(header);
		return header;		
	}
	
	public String[] getValues() {
		Field[] myFields = this.getClass().getFields();
		int length = myFields.length;
		String[] values=new String[length];
		for (int i=0; i<length;i++) {
			values[i]=getFieldValue(myFields[i]);
		}
		System.out.println(values);
		return values;		
	}
	
	private String getFieldValue(Field field) {
		
		String value=null;
		String type;
		
		try {
			type=field.getType().getName();
			if(type.equals("java.lang.String")) {
				value=(String)field.get(this);
			}else if(type.equals("int")) {
				value=Integer.toString(field.getInt(this));
			}else if(type=="double") {
				value=Double.toString(field.getDouble(this));
			}else if(type.equals("ch.nc.asset.Currency")) {
				Currency currency= (Currency)field.get(this);
				if(currency!=null) {
					value=currency.toString();
				} else {
					value="null";
				}
			}else if(type.equals("java.util.Date")) {
				Date date= (Date)field.get(this);
				if(date!=null) {
					value=Helper.simpleDateFormat.format(date);
				} else {
					value="null";
				}
			} else {
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
		return value;
	}
	
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
				if(type.equals("java.lang.String")) {
					value=(String)field.get(this);
				}else if(type.equals("int")) {
					value=Integer.toString(field.getInt(this));
				}else if(type=="double") {
					value=Double.toString(field.getDouble(this));
				}else if(type.equals("ch.nc.asset.Currency")) {
					Currency currency= (Currency)field.get(this);
					if(currency!=null) {
						value=currency.toString();
					} else {
						value="null";
					}
				}else if(type.equals("java.util.Date")) {
					Date date= (Date)field.get(this);
					if(date!=null) {
						value=Helper.simpleDateFormat.format(date);
					} else {
						value="null";
					}
				} else {
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
