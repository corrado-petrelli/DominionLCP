package com.sample;

public class Curse extends Card {

	private byte value;
	protected Curse() {
		super("Curse", "Curse", null, (byte) 0);
		this.value = -1;
	}
	public byte getValue() {
		return value;
	}
	
	

	@Override
	public String toString() {
		return "Curse [value=" + value + ", name=" + name + ", type=" + type + ", subtype=" + subtype + ", cost=" + cost
				+ "]";
	}
	public static void main(String[] args) {
		Card curse = new Curse();
		System.out.println(curse);
	}
}
