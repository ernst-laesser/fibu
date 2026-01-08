package ch.nc.fibu.imp;

import java.util.Date;

public class Booking {
	private Date date;
	private int soll;
	private int haben;
	private float betrag;
	private String text;
	private boolean isDouble;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSoll() {
		return soll;
	}
	public void setSoll(int soll) {
		this.soll = soll;
	}
	public int getHaben() {
		return haben;
	}
	public void setHaben(int haben) {
		this.haben = haben;
	}
	public float getBetrag() {
		return betrag;
	}
	public void setBetrag(float betrag) {
		this.betrag = betrag;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isDouble() {
		return isDouble;
	}
	public void setDouble(boolean isDouble) {
		this.isDouble = isDouble;
	}
	public String getTemp() {
		if (isDouble) {
			return "1";
		} 
		return "0";
	}

}
