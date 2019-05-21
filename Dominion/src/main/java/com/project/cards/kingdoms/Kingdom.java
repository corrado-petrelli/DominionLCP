package com.project.cards.kingdoms;

import com.project.cards.Card;

public abstract class Kingdom extends Card{

	public Kingdom(String name, String subtype, int cost) {
		super(name, "Kingdom", subtype, cost);
	}
}
