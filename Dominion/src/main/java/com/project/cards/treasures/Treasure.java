package com.project.cards.treasures;

import com.project.cards.Card;

public abstract class Treasure extends Card {

	private int value;
	
	public Treasure(String name, int cost, int value) {
		super(name, "Treasure", null, cost);
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String toString() {
		return "Card [value=" + value + ", name=" + name + ", type=" + type +", cost="
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
