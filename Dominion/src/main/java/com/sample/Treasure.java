package com.sample;

public class Treasure extends Card {

	private byte value;
	
	protected Treasure(String name, byte cost, byte value) {
		super(name, "Treasure", null, cost);
	}

	public byte getValue() {
		return value;
	}
	
	
	
	@Override
	public String toString() {
		return "Treasure [value=" + value + ", name=" + name + ", type=" + type + ", subtype=" + subtype + ", cost="
				+ cost + "]";
	}

	/*
	public static void main(String[] args) {
		byte uno = 1;
		byte zero = 0;
		Card copper = new Treasure("Copper", "Treasure", null,zero,uno);
		System.out.println(copper);
	}
	*/
}
