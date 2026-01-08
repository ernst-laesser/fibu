package ch.nc.fibu.imp.mm.item;


import java.util.Iterator;
import java.util.TreeMap;

public class ItemList {
	
	private TreeMap <String,Item> itemSet = new TreeMap<String,Item>();
	
	
	public ItemList() {
	}
	
	public Iterator<Item> iterator() {
		return itemSet.values().iterator();
	}
	
	public Item getItem(String name) {
		return itemSet.get(name);
	}
	
	public void add(Item item) {
		itemSet.put(item.getName(), item);
	}
	
	public String toString() {
		return itemSet.keySet().toString();
	}
	
}
