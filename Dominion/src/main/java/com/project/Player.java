package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.drools.compiler.lang.DRL5Expressions.instanceof_key_return;

import com.project.cards.Card;
import com.project.cards.IVictoryCard;
import com.project.cards.curses.Curse;
import com.project.cards.kingdoms.Kingdom;
import com.project.cards.treasures.Copper;
import com.project.cards.treasures.Gold;
import com.project.cards.treasures.Silver;
import com.project.cards.treasures.Treasure;
import com.project.cards.victories.Duchy;
import com.project.cards.victories.Estate;
import com.project.cards.victories.Province;

public class Player implements Comparable<Player>{
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discard;
	/**
	 * It represents ALL THE COINS of the player, also the virtual one
	 * VIRTUALCOINS = TREASURE COINS + COINS GAINED WITHOUT TREASURE CARDS
	 */
	private int virtualCoins;
	private int actions;
	private int purchases;
	private String username;
	
	public Player(String username) {
		super();
		this.deck = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.discard = new ArrayList<>();
		this.virtualCoins = 0;
		this.actions = 0;
		this.purchases = 0;
		this.username = username;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}
	public void decreasePurchases(){
		--purchases;
	}

	public int getActions() {
		return actions;
	}
	
	public void decreaseActions(){
		--actions;
	}
	public void setActions(int actions) {
		this.actions = actions;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public List<Card> getHand() {
		return hand;
	}
	
	
	
	public List<Card> getDiscard() {
		return discard;
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
	
	public void decreaseVirtualCoins(int costsOfCard){
		this.virtualCoins = this.virtualCoins - costsOfCard;
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
	
	public int getVictoryPoint(){
		int total = 0;
		for(Card card : hand){
			if(card instanceof IVictoryCard)
				total += ((IVictoryCard) card).getVictoryPoint();
		}
		return total;
	}
	
	public void buy(Card cardToBuy){
	int budget = this.getTotalCoins();
	int updatedBudget = 0;
		if(cardToBuy.getCost() <= budget){
			updatedBudget = (budget- cardToBuy.getCost());
			this.hand.add(cardToBuy);
			this.deck.remove(cardToBuy);

			/*
			 * TODO
			 * Check if cardToBuy cost > player's coin
			 * move the card from the common deck to the player's deck
			 */
		}
		else
		{
     System.out.println("The card" + cardToBuy + "is to expensive");	
     System.out.println("Your budget is :" + budget);
     System.out.println("Your cards are :"+ hand);
     
}
		this.deck.remove(cardToBuy);
		// la carta deve essere aggiunta ad deck comune 
		System.out.println("You have bought the"+ cardToBuy + "card!");	
		System.out.println("Your new budget is"+ updatedBudget);	
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
			this.discard.add(cardToDiscard);
			this.hand.remove(cardToDiscard);
			/*
			 * TODO
			 * 
			 * 
			 * I must move the cardToDiscard (check if I take this card in my hand)
			 * to the discarded deck.
			 */
		}
		else{
           System.out.println("The card" + cardToDiscard + " is non in your hand!");
		}
	}
	
	
	@Override
	public int compareTo(Player o) {
		if(this.getVictoryPoint() > o.getVictoryPoint())
			return 1;
		else if(this.getVictoryPoint() == o.getVictoryPoint())
			return 0;
		else
			return -1;
	}

	@Override
	public String toString() {
		return getUsername() + " ("+getVictoryPoint()+")";
	}
	
	public Copper getCopperCard(Table table) {
		if (table.getCopperDeck().size() > 0 && this.virtualCoins >= new Copper().getCost())
			return table.getCopperDeck().remove(0);
		return null;
	}

	public Silver getSilverCard(Table table) {
		if (table.getSilverDeck().size() > 0 && this.virtualCoins >= new Silver().getCost())
			return table.getSilverDeck().remove(0);
		return null;
	}

	public Gold getGoldCard(Table table) {
		if (table.getGoldDeck().size() > 0 && this.virtualCoins >= new Gold().getCost())
			return table.getGoldDeck().remove(0);
		return null;
	}

	public Estate getEstateCard(Table table) {
		if (table.getEstateDeck().size() > 0 && this.virtualCoins >= new Estate().getCost())
			return table.getEstateDeck().remove(0);
		return null;
	}

	public Duchy getDuchyCard(Table table) {
		if (table.getDuchyDeck().size() > 0 && this.virtualCoins >= new Duchy().getCost())
			return table.getDuchyDeck().remove(0);
		return null;
	}

	public Province getProvinceCard(Table table) {
		if (table.getProvinceDeck().size() > 0 && this.virtualCoins >= new Province().getCost())
			return table.getProvinceDeck().remove(0);
		return null;
	}

	public Curse getCurseCard(Table table) {
		if (table.getCurseDeck().size() > 0 && this.virtualCoins >= new Curse().getCost())
			return table.getCurseDeck().remove(0);
		return null;
	}

	public Kingdom getKingdomCard(Table table, int numberOfTheDeck) throws NumberFormatException, IOException {  	
    	ArrayList<Kingdom> chosenKingdomDeck = table.getKingdomDecks().get(numberOfTheDeck);

    	if(!chosenKingdomDeck.isEmpty())
    		return chosenKingdomDeck.remove(0);
    	
    	return null;
	}
	
	
	
	
	
	
	
	
	
}
