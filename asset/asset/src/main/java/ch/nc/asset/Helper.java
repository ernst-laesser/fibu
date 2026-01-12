package ch.nc.asset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Helper {
	
	public static String TextFollowing(String source, String text) {
		int pos = source.indexOf(text);
		pos = pos + text.length();
		StringBuffer sb = new StringBuffer();
		char c=source.charAt(pos);
		int l=source.length();
		while(c == ' ' && pos<l) {
			c=source.charAt(pos++);
		}
		sb.append(c);
		while(c != ' ' && pos<l) {
			c=source.charAt(pos++);
			sb.append(c);
			
		}
		return sb.toString();
	}
	
	public static double DoubleFollowing(String source, String text) {
		int pos = source.indexOf(text);
		pos = pos + text.length();
		StringBuffer sb = new StringBuffer();
		char c=source.charAt(pos);
		int l=source.length();
		while(c == ' ' && pos<l) {
			c=source.charAt(pos++);
		}
		sb.append(c);
		while(c != ' ' && pos<l) {
			c=source.charAt(pos++);
			sb.append(c);
			
		}
		return getDouble(sb.toString());
	}
	
	public static ArrayList<String> getTokens(String source) {
		ArrayList<String> tokenList = new ArrayList<String>();
		source=source.trim();
		char c;
		int state=1; 
		//state == 1 means iterating within token, state==0 means iterating within white space
		// transition from 1 to 0 means end of token
		// transition from 0 to 1 means end of whitespace, begin of new token;
		// since source is trimed  before iteration, we start with state==1
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<source.length();i++) {			
			c=source.charAt(i);
			if(Character.isWhitespace(c) && state==1) {
				// transition from 1 to 0 means end of token, begin of whitespace
				tokenList.add(sb.toString());
				state=0;
			}else  if (Character.isWhitespace(c) && state==0) {
				// do nothing, we are within white space
			}else  if (!Character.isWhitespace(c) && state==1) {
				// we are within token
				sb.append(c);
			}
			else  if (!Character.isWhitespace(c) && state==0) {
				//end of whitespace, begin of new token
				sb = new StringBuffer();
				sb.append(c);
				state=1;
			}
		}
		//since source is trimed  before iteration, string must end with token
		tokenList.add(sb.toString());
		return tokenList;
	}
	public static double getDouble(String string) {
		char c;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<string.length();i++) {
			c=string.charAt(i);
			if(c!='\'') {
				sb.append(c); // remove the thousend separator "'"
			}
		}
		Double d= Double.parseDouble(sb.toString());
		return d;
	}
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	public static Date getDateFollowing(String source, String text) {
		String dateString=TextFollowing(source, text);
		Date date;
		try {
			date = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date=null;
		}
		return date;
	}

	
	
}
