package com.sample;

public abstract class Card {
	protected String name;
	protected String type;
	protected String subtype;
	protected byte cost;
	
	protected Card(String name, String type, String subtype, byte cost) {
		super();
		this.name = name;
		this.type = type;
		this.subtype = subtype;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getSubtype() {
		return subtype;
	}
	public byte getCost() {
		return cost;
	}
	
	
}
