package ch.nc.asset.ta.zkb;

import java.util.Iterator;

public class Lines {
	
	private Iterator<String> iterator;
	private int counter=0;
	
	public void setIterator(Iterator<String> iterator) {
		this.iterator = iterator;
	}

	public String next() {
		String line = iterator.next();
		System.out.println(counter++ +": "+line);
		return line;
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}

	public int getCounter() {
		return counter;
	}

}
