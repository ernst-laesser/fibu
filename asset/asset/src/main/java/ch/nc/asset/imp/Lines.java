package ch.nc.asset.imp;

import java.util.Iterator;

public class Lines {
	
	private Iterator<String> iterator;
	private int counter=0;
	private boolean sysOut;
	
	public void setIterator(Iterator<String> iterator) {
		this.iterator = iterator;
	}

	public String next() {
		String line = iterator.next();
		if (sysOut) {
			System.out.println(counter++ + ": " + line);
		}
		return line;
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}

	public int getCounter() {
		return counter;
	}

	public void setSysOut(boolean sysOut) {
		this.sysOut = sysOut;
	}

}
