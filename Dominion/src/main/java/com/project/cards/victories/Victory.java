package com.project.cards.victories;

import com.project.cards.Card;

public class Victory extends Card {
	
	/**
	 * For value: the point of the winning
	 */
	private int value;
	public Victory(String name, int cost, int value) {
		super(name, "Kingdom", null, cost);
		this.value = value;
		
	}
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "Victory [value=" + value + ", name=" + name + ", type=" + type + ", subtype=" + subtype + ", cost="
				+ cost + "]";
	}
	
	

}
