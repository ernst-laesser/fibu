package ch.nc.fibu.imp;

import java.util.ArrayList;

public class BookingHandler {
	
	private BookingWriter bookingWriter;
	private ArrayList<BookingRuleBase> bookingRules;
	
	public BookingHandler(BookingWriter bookingWriter){
		this.bookingWriter = bookingWriter;
		bookingRules=new ArrayList<BookingRuleBase>();		
	}
	
	public void addBookingRule(BookingRuleBase bookingRule) {
		bookingRule.setWriter(bookingWriter);
		bookingRules.add(bookingRule);
	}
	
//	public void addBookingRule(String bookingRuleName) {
//		Class c = Class.forName(bookingRuleName);
//		bookingRule = BookingRuleBase(c);
//		bookingRule.setWriter(bookingWriter);
//		bookingRules.add(bookingRule);
//	}
	
	public void processBooking(RawBookingIF rawBooking) {
		for (BookingRuleBase rule: bookingRules) {
			if(rule.processBooking(rawBooking)) {
				break;
			}
		}
	}

}