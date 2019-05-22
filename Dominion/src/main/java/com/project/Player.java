package com.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.project.cards.Card;
import com.project.cards.kingdoms.Kingdom;
import com.project.cards.treasures.Treasure;

public class Player {
	private List<Card> deck;
	private List<Card> hand;
	/**
	 * There is a card that provide coins but not provide a treasure card
	 * this type of coin is called "virtual coin"
	 */
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

	public void addToHand(Card newCard){
		hand.add(newCard);
	}
	
	public void addToDeck(Card newCard){
		deck.add(newCard);
	}
	
	public void shuffleDeck(){
		Collections.shuffle(deck);
	}
	
	public int getVirtualCoins() {
		return virtualCoins;
	}

	public void setVirtualCoins(int virtualCoins) {
		this.virtualCoins = virtualCoins;
	}
	
	/**
	 * The total coins is calculated when i invoke the method
	 * @return
	 */
	public int getTotalCoins() {
		int total = 0;
		for (Card card : hand)
			if(card instanceof Treasure)
				total += card.getCost();
		return total + this.getVirtualCoins();
	}

	public String getUsername() {
		return username;
	}
	
	public void buy(Card cardToBuy)
	{
		if(cardToBuy.getCost() <= this.getTotalCoins()){
			/*
			 * TODO
			 * Check if cardToBuy cost > player's coin
			 * move the card from the common deck to the player's deck
			 */
		}
		else
		{
			//ERROR, YOU CAN'T BUY THIS CARD
		}
		
	}
	
	public void useCard(Kingdom kingdomToUse, List<Player> playersToAttack)
	{
		/*
		 * TODO
		 * 
		 * 		NB: 
		 * 			if playersToAttack == null -> it's a card that has not effect to the other players
		 * 			if playersToAttack has only one person -> the player attack only 1 person
		 * 			if playerToAttack has 2 or more player -> the player attack more player
		 */
	}
	
	public void discardCard(Card cardToDiscard){
		if(this.hand.contains(cardToDiscard)){
			/*
			 * TODO
			 * 
			 * 
			 * I must move the cardToDiscard (check if I take this card in my hand)
			 * to the discarded deck.
			 */
		}
		else{
			//ERROR
		}
	}
	
	
	
	
	
	
	
	
	
}
