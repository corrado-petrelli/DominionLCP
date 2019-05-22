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

        	List<Player> players = new ArrayList<Player>(3);
        	players.add(new Player("Corrado"));
        	players.add(new Player("Vincenzo"));
        	players.add(new Player("Fulvio"));
        	
        	
        	
        	//DECKS
        	ArrayList<Copper> copperDeck = new ArrayList<>();
        	ArrayList<Silver> silverDeck = new ArrayList<>();
        	ArrayList<Gold> goldDeck = new ArrayList<>();
        	ArrayList<Estate> estateDeck = new ArrayList<>();
        	ArrayList<Duchy> duchyDeck = new ArrayList<>();
        	ArrayList<Province> provinceDeck = new ArrayList<>();
        	ArrayList<Curse> curseDeck = new ArrayList<>();
        	ArrayList<Card> trashDeck = new ArrayList<>();
        	ArrayList<Kingdom> kingdomDeck = new ArrayList<>();
        	int j = 0;
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
				kingdomDeck.add(new Adventurer());
				kingdomDeck.add(new Bureaucrat());
				kingdomDeck.add(new Cellar());
				kingdomDeck.add(new Chancellor());
				kingdomDeck.add(new Chapel());
				kingdomDeck.add(new Councilroom());
				kingdomDeck.add(new Feast());
				kingdomDeck.add(new Festival());
				kingdomDeck.add(new Laboratory());
				kingdomDeck.add(new Library());
				kingdomDeck.add(new Market());
				kingdomDeck.add(new Militia());
				kingdomDeck.add(new Mine());
				kingdomDeck.add(new Moat());
				kingdomDeck.add(new Moneylender());
				kingdomDeck.add(new Remodel());
				kingdomDeck.add(new Smithy());
				kingdomDeck.add(new Spy());
				kingdomDeck.add(new Thief());
				kingdomDeck.add(new Throneroom());
				kingdomDeck.add(new Village());
				kingdomDeck.add(new Witch());
				kingdomDeck.add(new Woodcutter());
				kingdomDeck.add(new Workshop());
			}
			
			//12 gardens cards
			for (j = 0; j < 12; j++){
				kingdomDeck.add(new Gardens());
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
