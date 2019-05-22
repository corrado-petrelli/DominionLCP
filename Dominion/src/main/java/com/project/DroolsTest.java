package com.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.cards.Card;
import com.project.cards.curses.Curse;
import com.project.cards.kingdoms.*;
import com.project.cards.treasures.Copper;
import com.project.cards.treasures.Gold;
import com.project.cards.treasures.Silver;
import com.project.cards.victories.Duchy;
import com.project.cards.victories.Estate;
import com.project.cards.victories.Province;
import com.sun.media.jfxmedia.events.MarkerEvent;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	//Useful to the Gardens card
	public static Player actualPlayer;
	
    public static final void main(String[] args) {
        try {
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");


        	int j = 0;
        	List<Player> players = new ArrayList<Player>(3);
        	players.add(new Player("Corrado"));
        	players.add(new Player("Vincenzo"));
        	players.add(new Player("Fulvio"));
        	
        	
        	
        	//DECKS
        	List<Copper> copperDeck = new ArrayList<>();
        	List<Silver> silverDeck = new ArrayList<>();
        	List<Gold> goldDeck = new ArrayList<>();
        	List<Estate> estateDeck = new ArrayList<>();
        	List<Duchy> duchyDeck = new ArrayList<>();
        	List<Province> provinceDeck = new ArrayList<>();
        	List<Curse> curseDeck = new ArrayList<>();
        	List<Card> trashDeck = new ArrayList<>();
        	
        	List<Kingdom> allKingdomCard = new ArrayList<>();
        	
        	
        	List<List<? extends Kingdom>> kingdomDecks = new ArrayList<List<? extends Kingdom>>(10);
        	

        	
        	//60 cooper
        	for (j = 0; j < 60; j++)
        		copperDeck.add(new Copper());
        	//40 silver
        	for (j = 0; j < 40; j++)
        		silverDeck.add(new Silver());
        	//30 gold
        	for (j = 0; j < 30; j++)
        		goldDeck.add(new Gold());
			//20 curse
        	for (j = 0; j < 20; j++)
        		curseDeck.add(new Curse());
			//12 estate
        	//12 duchy
        	//12 province
        	for (j = 0; j < 12; j++){
        		estateDeck.add(new Estate());
        		provinceDeck.add(new Province());
        		duchyDeck.add(new Duchy());
        	}        		

        	//24 kingdom cards (10 per type)
			for (j = 0; j < 10; j++){
				allKingdomCard.add(new Adventurer());
				allKingdomCard.add(new Bureaucrat());
				allKingdomCard.add(new Cellar());
				allKingdomCard.add(new Chancellor());
				allKingdomCard.add(new Chapel());
				allKingdomCard.add(new Councilroom());
				allKingdomCard.add(new Feast());
				allKingdomCard.add(new Festival());
				allKingdomCard.add(new Laboratory());
				allKingdomCard.add(new Library());
				allKingdomCard.add(new Market());
				allKingdomCard.add(new Militia());
				allKingdomCard.add(new Mine());
				allKingdomCard.add(new Moat());
				allKingdomCard.add(new Moneylender());
				allKingdomCard.add(new Remodel());
				allKingdomCard.add(new Smithy());
				allKingdomCard.add(new Spy());
				allKingdomCard.add(new Thief());
				allKingdomCard.add(new Throneroom());
				allKingdomCard.add(new Village());
				allKingdomCard.add(new Witch());
				allKingdomCard.add(new Woodcutter());
				allKingdomCard.add(new Workshop());
			}
			
			//12 gardens cards
			for (j = 0; j < 12; j++){
				allKingdomCard.add(new Gardens());
			}

        	
			//Each player starts the game with the same cards:
			//7 coppers	& 3 estates
			//Each player	shuffles these cards and places them (his Deck)
			//face-down in his play area (the area near him on the table).
			//Now, each player draws 5 cards from his Deck. These cards are the
			//player’s hand.
			for (Player player : players) {
				for(j = 0; j < 7; j++)
					player.addToDeck(copperDeck.remove(0));
				for(j = 0; j < 3; j++)
					player.addToDeck(estateDeck.remove(0));
				player.shuffleDeck();
				for(j = 0; j < 5; j++)
					player.addToHand(player.getDeck().remove(0));
			}
			
			
        	kSession.fireAllRules();
        	
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
