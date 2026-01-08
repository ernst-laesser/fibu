package ch.nc.fibu.imp.mm.item;

import java.util.Date;



public class Item {
	
	static public boolean updateUsage = false;
	
	private Date date;
	private String name;
	private int account;
	private int usage;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public void updateUsage() {
		if (updateUsage) {
			usage++;
		}
	}
	
	

}
