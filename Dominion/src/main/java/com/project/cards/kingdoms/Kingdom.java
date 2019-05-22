package com.project.cards.kingdoms;

import com.project.cards.Card;

public abstract class Kingdom extends Card{

	public Kingdom(String name, String subtype, int cost) {
		super(name, "Kingdom", subtype, cost);
	}

	@Override
	public String toString() {
		if(subtype == null)
			return "Card [name=" + name + ", type=" + type + ", cost=" + cost + "]";
		else
			return "Card [name=" + name + ", type=" + type + ", subtype=" + subtype + ", cost=" + cost + "]";
	}
	
	
}
