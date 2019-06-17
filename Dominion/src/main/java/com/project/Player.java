package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
		this.actions = 1;
		this.purchases = 1;
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
	
	public String getHandPrintable(){
		StringBuilder buff = new StringBuilder();
		buff.append("| ");
		for(Card c : hand){
			buff.append(c.getName());
			buff.append(" | ");
		}
		return buff.toString();
	}
	
	public Card getPlayableKingdomCardFromHand() {
		Collections.shuffle(this.getHand());
		Card c = null; int i = 0;
		try {
			for(; i < this.getHand().size(); i++) {
				if(this.getHand().get(i) instanceof Kingdom) {
					c = this.getHand().get(i);
					break;
				}
				c = null;
			}
			if(c != null) {
				this.getDiscard().add(c);
				this.getHand().remove(i);
			}
			return c;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void playAllTreasureCardsInHand() {
		//Player plays all treasure cards in hand
				Iterator<Card> it = this.getHand().iterator();
				while(it.hasNext()) {
					
					Card c = it.next();
					
					if(c instanceof Treasure) {
						this.setVirtualCoins(this.getVirtualCoins() + ((Treasure)c).getValue());
						this.getDiscard().add(c);
						it.remove();
					}
				}
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
		if(getDiscard().size() > 0) {
			deck.addAll(getDiscard());
			getDiscard().clear();
		}
		if(getHand().size() > 0) {
			deck.addAll(getHand());
			getHand().clear();
		}
		for(Card card : deck){
			//System.out.println(card.getName());
			if(card instanceof IVictoryCard)
				total += ((IVictoryCard) card).getVictoryPoint();
		}
		return total;
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
	
	public String playerInfo() {
		return 
				getUsername() + " situation: " + "\n"+
				"\tHand: "+getHandPrintable() + "\n"+
				"\tDeck size: " + getDeck().size() + "\n"+
				"\tDiscard size: "+getDiscard().size();
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
