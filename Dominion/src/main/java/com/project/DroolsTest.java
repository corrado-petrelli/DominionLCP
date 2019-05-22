package com.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        	List<Card> deck = new ArrayList<Card>();
        	int j = 0;
        	//60 cooper
        	for (j = 0; j < 60; j++)
        		deck.add(new Copper());
        	//40 silver
        	for (j = 0; j < 40; j++)
        		deck.add(new Silver());
        	//30 gold
        	for (j = 0; j < 30; j++)
        		deck.add(new Gold());
			//20 curse
        	for (j = 0; j < 20; j++)
        		deck.add(new Curse());
			//12 estate
        	//12 duchy
        	//12 province
        	for (j = 0; j < 12; j++){
        		deck.add(new Estate());
        		deck.add(new Province());
        		deck.add(new Duchy());
        	}        		

        	//24 kingdom cards (10 per type)
			for (j = 0; j < 10; j++){
				deck.add(new Adventurer());
				deck.add(new Bureaucrat());
				deck.add(new Cellar());
				deck.add(new Chancellor());
				deck.add(new Chapel());
				deck.add(new Councilroom());
				deck.add(new Feast());
				deck.add(new Festival());
				deck.add(new Laboratory());
				deck.add(new Library());
				deck.add(new Market());
				deck.add(new Militia());
				deck.add(new Mine());
				deck.add(new Moat());
				deck.add(new Moneylender());
				deck.add(new Remodel());
				deck.add(new Smithy());
				deck.add(new Spy());
				deck.add(new Thief());
				deck.add(new Throneroom());
				deck.add(new Village());
				deck.add(new Witch());
				deck.add(new Woodcutter());
				deck.add(new Workshop());
			}
			
			//12 gardens cards
			for (j = 0; j < 12; j++){
				deck.add(new Gardens());
			}

        	
        	int count = 0;        	
        	System.out.println("Total: "+count);
        	for (Card card : deck){
				System.out.println(card);
        		++count;
        	}
        	System.out.println("Total: "+count);

        	Player corrado = new Player("Corrado");
        	Player vincenzo = new Player("Vincenzo");
        	Player fulvio = new Player("Fulvio");
        	
        	//Collections.shuffle(deck);
        	
        	kSession.fireAllRules();
        	
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
