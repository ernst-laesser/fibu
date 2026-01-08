package ch.nc.fibu.imp;

import java.util.Date;

public interface RawBookingIF {
	
	// Methods used by either BookingRuleBase or BR_Default
	public Date getDate();
	public float 	getAmount();
	public String getText();
	public boolean isExpense();

}
