package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.project.cards.Card;
import com.project.cards.curses.Curse;
import com.project.cards.kingdoms.Bandit;
import com.project.cards.kingdoms.Cellar;
import com.project.cards.kingdoms.Kingdom;
import com.project.cards.kingdoms.Market;
import com.project.cards.kingdoms.Militia;
import com.project.cards.kingdoms.Mine;
import com.project.cards.kingdoms.Moat;
import com.project.cards.kingdoms.Remodel;
import com.project.cards.kingdoms.Smithy;
import com.project.cards.kingdoms.Village;
import com.project.cards.kingdoms.Woodcutter;
import com.project.cards.kingdoms.Workshop;
import com.project.cards.treasures.Copper;
import com.project.cards.treasures.Gold;
import com.project.cards.treasures.Silver;
import com.project.cards.victories.Duchy;
import com.project.cards.victories.Estate;
import com.project.cards.victories.Province;

/*
 * Class that contains all cards in the tables
 */
public class Table {
	
	/*
	 * 0 -> To initialize
	 * 1 -> To normal situation
	 * 2 -> End game situation (Province card pile is empty or any 3 Supply piles are empty)
	 */
	private int situation = 0;
	
	//Treasure
	private List<Copper> copperDeck = new ArrayList<>();
	private List<Silver> silverDeck = new ArrayList<>();
	private List<Gold> goldDeck = new ArrayList<>();

	//Victories
	private List<Estate> estateDeck = new ArrayList<>();
	private List<Duchy> duchyDeck = new ArrayList<>();
	private List<Province> provinceDeck = new ArrayList<>();

	//Curses
	private List<Curse> curseDeck = new ArrayList<>();

	//Trash
	private List<Card> trashDeck = new ArrayList<>();

	//Kingdoms
	//It's better if keys are string when we deal with the decks during buy and clean-up phases
	private Map<Integer, ArrayList<Kingdom>> kingdomDecks = new HashMap<Integer, ArrayList<Kingdom>>();


	/*
	 * Initialization of the game
	 */
	public Table() {
		situation = 0;
		int j = 0;
		// Setup - Recommended Configuration
		kingdomDecks.put(0, new ArrayList<Kingdom>() {{for(int i = 0; i < 10; i++) add(new Cellar());}});
		kingdomDecks.put(1, new ArrayList<Kingdom>() {{for(int i = 0; i < 10; i++) add(new Market());}});
		kingdomDecks.put(2, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Militia());}});
		kingdomDecks.put(3, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Mine());}});
		kingdomDecks.put(4, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Moat());}});
		kingdomDecks.put(5, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Remodel());}});
		kingdomDecks.put(6, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Smithy());}});
		kingdomDecks.put(7, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Village());}});
		kingdomDecks.put(8, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Woodcutter());}});
		kingdomDecks.put(9, new ArrayList<Kingdom>(){{for(int i = 0; i < 10; i++) add(new Workshop());}});

		// 60 cooper
		for (j = 0; j < 60; j++)
			copperDeck.add(new Copper());
		// 40 silver
		for (j = 0; j < 40; j++)
			silverDeck.add(new Silver());
		// 30 gold
		for (j = 0; j < 30; j++)
			goldDeck.add(new Gold());
		// 20 curse
		for (j = 0; j < 20; j++)
			curseDeck.add(new Curse());
		// 12 estate
		// 12 duchy
		// 12 province
		for (j = 0; j < 12; j++) {
			estateDeck.add(new Estate());
			provinceDeck.add(new Province());
			duchyDeck.add(new Duchy());
		}
		situation = 1;
	}

	/*
	 * 
	 */
	public void getCardsToThePlayers(List<Player> players) {
		int j = 0;
		// Each player starts the game with the same cards:
		// 7 coppers & 3 estates
		// Each player shuffles these cards and places them (his Deck)
		// face-down in his play area (the area near him on the table).
		// Now, each player draws 5 cards from his Deck. These cards are the
		// player’s hand.
		for (Player player : players) {
			for (j = 0; j < 7; j++)
				player.addToDeck(copperDeck.remove(0));
			for (j = 0; j < 3; j++)
				player.addToDeck(estateDeck.remove(0));
			player.shuffleDeck();
			
		
			for (j = 0; j < 5; j++)
				player.addToHand(player.getDeck().remove(0));
		}
	}
	
	public int checkDeckSize(Card typeOfDeck) {
		if(typeOfDeck instanceof Copper)
			return this.getCopperDeck().size();
		if(typeOfDeck instanceof Silver)
			return this.getSilverDeck().size();
		if(typeOfDeck instanceof Gold)
			return this.getGoldDeck().size();
		if(typeOfDeck instanceof Estate)
			return this.getEstateDeck().size();
		if(typeOfDeck instanceof Duchy)
			return this.getDuchyDeck().size();
		if(typeOfDeck instanceof Province)
			return this.getProvinceDeck().size();
		if(kingdomDecks.get(0).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(0).size();
		if(kingdomDecks.get(1).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(1).size();
		if(kingdomDecks.get(2).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(2).size();
		if(kingdomDecks.get(3).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(3).size();
		if(kingdomDecks.get(4).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(4).size();
		if(kingdomDecks.get(5).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(5).size();
		if(kingdomDecks.get(6).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(6).size();
		if(kingdomDecks.get(7).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(7).size();
		if(kingdomDecks.get(8).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(8).size();
		if(kingdomDecks.get(9).get(0).getClass().isInstance(typeOfDeck))
			return kingdomDecks.get(9).size();
		return 0;
	}

	public List<Card> getAllCardsThatCanBeBoughtWithAmountOfCoins(int amount) throws InstantiationException, IllegalAccessException {
		List<Card> cards = new ArrayList<Card>();
		if(amount >= new Copper().getCost() && this.getCopperDeck().size() > 0)
			cards.add(new Copper());
		if(amount >= new Silver().getCost() && this.getSilverDeck().size() > 0)
			cards.add(new Silver());
		if(amount >= new Gold().getCost() && this.getGoldDeck().size() > 0)
			cards.add(new Gold());
		if(amount >= new Estate().getCost() && this.getEstateDeck().size() > 0)
			cards.add(new Estate());
		if(amount >= new Duchy().getCost() && this.getDuchyDeck().size() > 0)
			cards.add(new Duchy());
		if(amount >= new Province().getCost() && this.getProvinceDeck().size() > 0)
			cards.add(new Province());
		if(kingdomDecks.get(0).size() > 0 && amount >= kingdomDecks.get(0).get(0).getCost())
			cards.add(kingdomDecks.get(0).get(0).getClass().newInstance());
		if(kingdomDecks.get(1).size() > 0 && amount >= kingdomDecks.get(1).get(0).getCost())
			cards.add(kingdomDecks.get(1).get(0).getClass().newInstance());
		if(kingdomDecks.get(2).size() > 0 && amount >= kingdomDecks.get(2).get(0).getCost())
			cards.add(kingdomDecks.get(2).get(0).getClass().newInstance());
		if(kingdomDecks.get(3).size() > 0 && amount >= kingdomDecks.get(3).get(0).getCost())
			cards.add(kingdomDecks.get(3).get(0).getClass().newInstance());
		if(kingdomDecks.get(4).size() > 0 && amount >= kingdomDecks.get(4).get(0).getCost())
			cards.add(kingdomDecks.get(4).get(0).getClass().newInstance());
		if(kingdomDecks.get(5).size() > 0 && amount >= kingdomDecks.get(5).get(0).getCost())
			cards.add(kingdomDecks.get(5).get(0).getClass().newInstance());
		if(kingdomDecks.get(6).size() > 0 && amount >= kingdomDecks.get(6).get(0).getCost())
			cards.add(kingdomDecks.get(6).get(0).getClass().newInstance());
		if(kingdomDecks.get(7).size() > 0 && amount >= kingdomDecks.get(7).get(0).getCost())
			cards.add(kingdomDecks.get(7).get(0).getClass().newInstance());
		if(kingdomDecks.get(8).size() > 0 && amount >= kingdomDecks.get(8).get(0).getCost())
			cards.add(kingdomDecks.get(8).get(0).getClass().newInstance());
		if(kingdomDecks.get(9).size() > 0 && amount >= kingdomDecks.get(9).get(0).getCost())
			cards.add(kingdomDecks.get(9).get(0).getClass().newInstance());
		return cards;
	}
	
	public List<Card> getAllCardsThatCanBeGainedUpToAmount(int amount) throws InstantiationException, IllegalAccessException {
		List<Card> cards = new ArrayList<Card>();
		if(amount <= new Copper().getCost() && this.getCopperDeck().size() > 0)
			cards.add(new Copper());
		if(amount <= new Silver().getCost() && this.getSilverDeck().size() > 0)
			cards.add(new Silver());
		if(amount <= new Gold().getCost() && this.getGoldDeck().size() > 0)
			cards.add(new Gold());
		if(amount <= new Estate().getCost() && this.getEstateDeck().size() > 0)
			cards.add(new Estate());
		if(amount <= new Duchy().getCost() && this.getDuchyDeck().size() > 0)
			cards.add(new Duchy());
		if(amount <= new Province().getCost() && this.getProvinceDeck().size() > 0)
			cards.add(new Province());
		if(kingdomDecks.get(0).size() > 0 && amount <= kingdomDecks.get(0).get(0).getCost())
			cards.add(kingdomDecks.get(0).get(0).getClass().newInstance());
		if(kingdomDecks.get(1).size() > 0 && amount <= kingdomDecks.get(1).get(0).getCost())
			cards.add(kingdomDecks.get(1).get(0).getClass().newInstance());
		if(kingdomDecks.get(2).size() > 0 && amount <= kingdomDecks.get(2).get(0).getCost())
			cards.add(kingdomDecks.get(2).get(0).getClass().newInstance());
		if(kingdomDecks.get(3).size() > 0 && amount <= kingdomDecks.get(3).get(0).getCost())
			cards.add(kingdomDecks.get(3).get(0).getClass().newInstance());
		if(kingdomDecks.get(4).size() > 0 && amount <= kingdomDecks.get(4).get(0).getCost())
			cards.add(kingdomDecks.get(4).get(0).getClass().newInstance());
		if(kingdomDecks.get(5).size() > 0 && amount <= kingdomDecks.get(5).get(0).getCost())
			cards.add(kingdomDecks.get(5).get(0).getClass().newInstance());
		if(kingdomDecks.get(6).size() > 0 && amount <= kingdomDecks.get(6).get(0).getCost())
			cards.add(kingdomDecks.get(6).get(0).getClass().newInstance());
		if(kingdomDecks.get(7).size() > 0 && amount <= kingdomDecks.get(7).get(0).getCost())
			cards.add(kingdomDecks.get(7).get(0).getClass().newInstance());
		if(kingdomDecks.get(8).size() > 0 && amount <= kingdomDecks.get(8).get(0).getCost())
			cards.add(kingdomDecks.get(8).get(0).getClass().newInstance());
		if(kingdomDecks.get(9).size() > 0 && amount <= kingdomDecks.get(9).get(0).getCost())
			cards.add(kingdomDecks.get(9).get(0).getClass().newInstance());
		return cards;
	}
	
	public void decreaseDeckSize(Card c) {
		if(c.getClass().equals(new Copper().getClass()) && this.getCopperDeck().size() > 0)
			this.getCopperDeck().remove(0);
		if(c.getClass().equals(new Silver().getClass()) && this.getCopperDeck().size() > 0)
			this.getSilverDeck().remove(0);
		if(c.getClass().equals(new Gold().getClass()) && this.getCopperDeck().size() > 0)
			this.getGoldDeck().remove(0);
		if(c.getClass().equals(new Estate().getClass()) && this.getCopperDeck().size() > 0)
			this.getEstateDeck().remove(0);
		if(c.getClass().equals(new Duchy().getClass()) && this.getCopperDeck().size() > 0)
			this.getDuchyDeck().remove(0);
		if(c.getClass().equals(new Province().getClass()) && this.getCopperDeck().size() > 0)
			this.getProvinceDeck().remove(0);
		if(kingdomDecks.get(0).size() > 0 && c.getClass().equals(kingdomDecks.get(0).get(0).getClass()))
			kingdomDecks.get(0).remove(0);
		if(kingdomDecks.get(1).size() > 0 && c.getClass().equals(kingdomDecks.get(1).get(0).getClass()))
			kingdomDecks.get(1).remove(0);
		if(kingdomDecks.get(2).size() > 0 && c.getClass().equals(kingdomDecks.get(2).get(0).getClass()))
			kingdomDecks.get(2).remove(0);
		if(kingdomDecks.get(3).size() > 0 && c.getClass().equals(kingdomDecks.get(3).get(0).getClass()))
			kingdomDecks.get(3).remove(0);
		if(kingdomDecks.get(4).size() > 0 && c.getClass().equals(kingdomDecks.get(4).get(0).getClass()))
			kingdomDecks.get(4).remove(0);
		if(kingdomDecks.get(5).size() > 0 && c.getClass().equals(kingdomDecks.get(5).get(0).getClass()))
			kingdomDecks.get(5).remove(0);
		if(kingdomDecks.get(6).size() > 0 && c.getClass().equals(kingdomDecks.get(6).get(0).getClass()))
			kingdomDecks.get(6).remove(0);
		if(kingdomDecks.get(7).size() > 0 && c.getClass().equals(kingdomDecks.get(7).get(0).getClass()))
			kingdomDecks.get(7).remove(0);
		if(kingdomDecks.get(8).size() > 0 && c.getClass().equals(kingdomDecks.get(8).get(0).getClass()))
			kingdomDecks.get(8).remove(0);
		if(kingdomDecks.get(9).size() > 0 && c.getClass().equals(kingdomDecks.get(9).get(0).getClass()))
			kingdomDecks.get(9).remove(0);
				
	}
	
	public int getNumberOfEmptySupplyDeck() {
		int emptySupplies = 0;
		if(this.getCopperDeck().isEmpty())
			emptySupplies++;
		if(this.getSilverDeck().isEmpty())
			emptySupplies++;
		if(this.getGoldDeck().isEmpty())
			emptySupplies++;
		if(this.getEstateDeck().isEmpty())
			emptySupplies++;
		if(this.getDuchyDeck().isEmpty())
			emptySupplies++;
		if(this.getProvinceDeck().isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(0).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(1).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(2).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(3).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(4).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(5).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(6).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(7).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(8).isEmpty())
			emptySupplies++;
		if(kingdomDecks.get(9).isEmpty())
			emptySupplies++;
		return emptySupplies;
	}

	public List<Card> getTrashDeck() {
		return trashDeck;
	}

	public Map<Integer, ArrayList<Kingdom>> getKingdomDecks() {
		return kingdomDecks;
	}
	
	public List<Copper> getCopperDeck() {
		return copperDeck;
	}

	public List<Silver> getSilverDeck() {
		return silverDeck;
	}

	public List<Gold> getGoldDeck() {
		return goldDeck;
	}

	public List<Estate> getEstateDeck() {
		return estateDeck;
	}

	public List<Duchy> getDuchyDeck() {
		return duchyDeck;
	}

	public List<Province> getProvinceDeck() {
		return provinceDeck;
	}

	public List<Curse> getCurseDeck() {
		return curseDeck;
	}
	
	public void checkEndGame(){
		if(provinceDeck.isEmpty()){
			System.out.println("***The province deck is empty!***");
			situation = 2;
		}
		else{
			int count = 0;
			if(copperDeck.isEmpty())
				count++;
			if(silverDeck.isEmpty())
				count++;
			if(goldDeck.isEmpty())
				count++;
			
			if(estateDeck.isEmpty())
				count++;
			if(duchyDeck.isEmpty())
				count++;
			
			
			if(curseDeck.isEmpty())
				count++;
			if(trashDeck.isEmpty())
				count++;
			
			for (ArrayList<Kingdom> arrayOfKingdoms : kingdomDecks.values())
			    if(arrayOfKingdoms.isEmpty())
			    	count++;
			
			//THE GAME END!
			if(count >= 3){
				situation = 2;
				System.out.println("***Three or more supply piles are empty***");
			}
		}
		
	}

	public int getSituation() {
		return situation;
	}

}
