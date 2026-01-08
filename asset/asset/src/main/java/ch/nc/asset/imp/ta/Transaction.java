package ch.nc.asset.imp.ta;

import java.util.Date;

import ch.nc.asset.Currency;

public class Transaction {
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
	

}
