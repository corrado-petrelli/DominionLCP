package com.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.cards.Card;
import com.project.cards.curses.Curse;
import com.project.cards.treasures.Copper;
import com.project.cards.treasures.Gold;
import com.project.cards.treasures.Silver;
import com.project.cards.victories.Duchy;
import com.project.cards.victories.Estate;
import com.project.cards.victories.Province;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

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
        	for (j = 0; j < 12; j++)
        		deck.add(new Estate());
        	//12 duchy
        	for (j = 0; j < 12; j++)
        		deck.add(new Duchy());
        	//12 province
        	for (j = 0; j < 12; j++)
        		deck.add(new Province());
            /*
             * TODO: You must insert the kingdom cards!
             */
        	
        	int count = 0;        	
        	System.out.println("Total: "+count);
        	for (Card card : deck){
				System.out.println(card);
        		++count;
        	}

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
