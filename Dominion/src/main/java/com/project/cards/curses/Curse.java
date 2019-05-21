package com.project.cards.curses;

import com.project.cards.Card;

public class Curse extends Card {

	private byte value;
	public Curse() {
		super("Curse", "Curse", null,0);
		this.value = -1;
	}
	public int getValue() {
		return value;
	}
	
	

	
	public String toString() {
		return "Curse [value=" + value + ", name=" + name + ", type=" + type + ", subtype=" + subtype + ", cost=" + cost
				+ "]";
	}
	public static void main(String[] args) {
		Card curse = new Curse();
		System.out.println(curse);
	}
}
