package ch.nc.asset.test;

import java.util.ArrayList;

import ch.nc.asset.Helper;

public class Main {

	public static void main(String[] args) {
		
		String input="Stück USD USD 250 zu 100.45 25'112.50";
		ArrayList<String> tokenList = Helper.getTokens(input);
		System.out.println("getTokens: "+tokenList.toString());
		
		input="Valor 55021050 / ISIN US46436E7186";
		tokenList = Helper.getTokens(input);
		System.out.println("getTokens: "+tokenList.toString());
		
		input="Valor 55021050 / ISIN US46436E7186";
		String result=Helper.TextFollowing(input, "ISIN");
		System.out.println("TextFollowing: "+result);
				

	}

}
