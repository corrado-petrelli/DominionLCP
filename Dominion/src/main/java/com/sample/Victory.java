package com.sample;

public class Victory extends Card {
	
	/**
	 * For value: the point of the winning
	 */
	private byte value;
	protected Victory(String name, byte cost, byte value) {
		super(name, "Kingdom", null, cost);
		this.value = value;
		// TODO Auto-generated constructor stub
	}
	public byte getValue() {
		return value;
	}
	@Override
	public String toString() {
		return "Victory [value=" + value + ", name=" + name + ", type=" + type + ", subtype=" + subtype + ", cost="
				+ cost + "]";
	}
	
	

}
