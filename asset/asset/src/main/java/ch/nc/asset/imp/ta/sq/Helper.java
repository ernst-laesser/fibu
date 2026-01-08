package ch.nc.asset.imp.ta.sq;

import ch.nc.asset.Amount;

public class Helper {
	
	

	public static Amount AmountFollowing(String source, String text) {
		int pos = source.indexOf(text);
		pos = pos + text.length();
		StringBuffer sb = new StringBuffer();

		while (source.charAt(pos++) == ' ')
			
		// get Currency
		while (source.charAt(pos) != ' ') {
			sb.append(source.charAt(pos++));
		}
		String currency = sb.toString();
		// get Value
		sb = new StringBuffer();
		while (source.charAt(pos++) == ' ');
		while (source.charAt(pos) != ' ') {
			sb.append(source.charAt(pos++));
		}
		String value=sb.toString();
		
		Amount amount = new Amount();
		amount.setCurreny(currency);
		amount.setValue(value);
		return amount;
	}
}
