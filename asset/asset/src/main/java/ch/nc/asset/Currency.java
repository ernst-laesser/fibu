package ch.nc.asset;

public enum Currency {
	CHF, USD, EUR, GBP, SID;

	public static Currency getCurrency(String s) {

		for (Currency cur : Currency.values()) {
			if (cur.name().equals(s)) {
				return cur;
			}
		}
		return null;
	}

}
