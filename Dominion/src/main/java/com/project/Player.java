package com.sample;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> deck;
	private List<Card> hand;
	private int virtualCoins;
	private String username;
	
	public Player(String username) {
		super();
		this.deck = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.virtualCoins = 0;
		this.username = username;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getVirtualCoins() {
		return virtualCoins;
	}

	public void setVirtualCoins(int virtualCoins) {
		this.virtualCoins = virtualCoins;
	}

	public String getUsername() {
		return username;
	}
	
	
	
	
	
}
