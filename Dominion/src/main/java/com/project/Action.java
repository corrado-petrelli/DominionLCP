package com.project;

public enum Action {
	//Draw a card (with coin)
	DRAW,
	//Apply the effect of a card
	USE,
	//Gain some coins
	COIN,
	//Buy a card
	BUY,
	//Draw a card (without coin!! && not from your deck,
	//it depends from the effect from the effect of the card)
	GET,
	PENDINGSILVER
}
