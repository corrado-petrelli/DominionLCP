package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;

import com.project.cards.Card;
import com.project.cards.curses.Curse;
import com.project.cards.kingdoms.*;
import com.project.cards.treasures.Copper;
import com.project.cards.treasures.Gold;
import com.project.cards.treasures.Silver;
import com.project.cards.treasures.Treasure;
import com.project.cards.victories.Duchy;
import com.project.cards.victories.Estate;
import com.project.cards.victories.Province;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;
/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	//For Garden card
	public static Player actualPlayer;
	//To input
	public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
	
    public static final void main(String[] args) throws IOException {
    	TeeOutputStream tee = null;
        try {
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

        	/*
				███████╗███████╗████████╗██╗   ██╗██████╗      ██████╗ ██╗   ██╗████████╗
				██╔════╝██╔════╝╚══██╔══╝██║   ██║██╔══██╗    ██╔═══██╗██║   ██║╚══██╔══╝
				███████╗█████╗     ██║   ██║   ██║██████╔╝    ██║   ██║██║   ██║   ██║   
				╚════██║██╔══╝     ██║   ██║   ██║██╔═══╝     ██║   ██║██║   ██║   ██║   
				███████║███████╗   ██║   ╚██████╔╝██║         ╚██████╔╝╚██████╔╝   ██║   
				╚══════╝╚══════╝   ╚═╝    ╚═════╝ ╚═╝          ╚═════╝  ╚═════╝    ╚═╝   	                                                                         
        	 */
        	
        	//Name generator
            Date date=new Timestamp(System.currentTimeMillis());    
            String data = date.toString();
            data = data.replace(" ", "-");
            data = data.replace(".", "-");
            //Set standard output
            PrintStream console = System.out;
            PrintStream fileOut = new PrintStream("./MATCH_"+data.replace(":", "-")+".txt");
            tee = new TeeOutputStream(console, fileOut);
            System.setOut(new PrintStream(tee));
        	/*
				███████╗███████╗████████╗██╗   ██╗██████╗      ██████╗  █████╗ ███╗   ███╗███████╗
				██╔════╝██╔════╝╚══██╔══╝██║   ██║██╔══██╗    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝
				███████╗█████╗     ██║   ██║   ██║██████╔╝    ██║  ███╗███████║██╔████╔██║█████╗  
				╚════██║██╔══╝     ██║   ██║   ██║██╔═══╝     ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  
				███████║███████╗   ██║   ╚██████╔╝██║         ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗
				╚══════╝╚══════╝   ╚═╝    ╚═════╝ ╚═╝          ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝	                                                                                                                    
        	 */
        	List<Player> players = new ArrayList<Player>();
        	players.add(new Player("Conrad"));
        	players.add(new Player("Vincent"));
        	players.add(new Player("Francisco"));

        	//Randomly determine the starting player.
        	Collections.shuffle(players);
        	
        	//The setup is into the Table constructor
        	Table table = new Table();
        	//Initial cards distribution
        	table.getCardsToThePlayers(players);
        	
        	
        	
        	Random r = new Random();
        	
        	kSession.setGlobal("players", players);
        	kSession.setGlobal("table", table);
        	kSession.setGlobal("gameLogic", r);
        	kSession.setGlobal("trashPile", table.getTrashDeck());
			     	
			/*
				██████╗ ██╗      █████╗ ██╗   ██╗
				██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝
				██████╔╝██║     ███████║ ╚████╔╝ 
				██╔═══╝ ██║     ██╔══██║  ╚██╔╝  
				██║     ███████╗██║  ██║   ██║   
				╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   				                                 
			 */
        	//The actual player is the first of the list
			actualPlayer = players.get(0);
			kSession.insert(actualPlayer);
			System.out.println(Color.RED_BOLD+"Now it's the turn of " +Color.RESET+Color.PURPLE+ actualPlayer.getUsername()+Color.RESET);
				
			/*
				 █████╗  ██████╗████████╗██╗ ██████╗ ███╗   ██╗    ██████╗ ██╗  ██╗ █████╗ ███████╗███████╗
				██╔══██╗██╔════╝╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔══██╗██║  ██║██╔══██╗██╔════╝██╔════╝
				███████║██║        ██║   ██║██║   ██║██╔██╗ ██║    ██████╔╝███████║███████║███████╗█████╗  
				██╔══██║██║        ██║   ██║██║   ██║██║╚██╗██║    ██╔═══╝ ██╔══██║██╔══██║╚════██║██╔══╝  
				██║  ██║╚██████╗   ██║   ██║╚██████╔╝██║ ╚████║    ██║     ██║  ██║██║  ██║███████║███████╗
				╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝    ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝
			 */
			
			
			kSession.insert(Phase.ACTION);
			//I must trigger "Choice Action Phase Rule" and "Play XXXXXX Card"
			//Where XXXXXX is a Kingdom card, the logic is in Drools
			kSession.fireAllRules();
			
			
			/*
				██████╗ ██╗   ██╗██╗   ██╗    ██████╗ ██╗  ██╗ █████╗ ███████╗███████╗
				██╔══██╗██║   ██║╚██╗ ██╔╝    ██╔══██╗██║  ██║██╔══██╗██╔════╝██╔════╝
				██████╔╝██║   ██║ ╚████╔╝     ██████╔╝███████║███████║███████╗█████╗  
				██╔══██╗██║   ██║  ╚██╔╝      ██╔═══╝ ██╔══██║██╔══██║╚════██║██╔══╝  
				██████╔╝╚██████╔╝   ██║       ██║     ██║  ██║██║  ██║███████║███████╗
				╚═════╝  ╚═════╝    ╚═╝       ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝
				                                                                      
			 */
			
			
			/*
			 * 	PURCHASE PHASE IS MANAGED INSIDE DROOLS
			 */
			
			/*
			 ██████╗██╗     ███████╗ █████╗ ███╗   ██╗      ██╗   ██╗██████╗     ██████╗ ██╗  ██╗ █████╗ ███████╗███████╗
			██╔════╝██║     ██╔════╝██╔══██╗████╗  ██║      ██║   ██║██╔══██╗    ██╔══██╗██║  ██║██╔══██╗██╔════╝██╔════╝
			██║     ██║     █████╗  ███████║██╔██╗ ██║█████╗██║   ██║██████╔╝    ██████╔╝███████║███████║███████╗█████╗  
			██║     ██║     ██╔══╝  ██╔══██║██║╚██╗██║╚════╝██║   ██║██╔═══╝     ██╔═══╝ ██╔══██║██╔══██║╚════██║██╔══╝  
			╚██████╗███████╗███████╗██║  ██║██║ ╚████║      ╚██████╔╝██║         ██║     ██║  ██║██║  ██║███████║███████╗
			 ╚═════╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝       ╚═════╝ ╚═╝         ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝
			 */
			
			/*
			 * CLEANUP PHASE IS MANAGED INSIDE DROOLS
			 */
			
			// Check end game condition, is done in drools
			//Switch the turn is moved into drools
			
			/*
			████████╗██╗  ██╗███████╗    ██╗    ██╗██╗███╗   ██╗███╗   ██╗███████╗██████╗     ██╗███████╗
			╚══██╔══╝██║  ██║██╔════╝    ██║    ██║██║████╗  ██║████╗  ██║██╔════╝██╔══██╗    ██║██╔════╝
			   ██║   ███████║█████╗      ██║ █╗ ██║██║██╔██╗ ██║██╔██╗ ██║█████╗  ██████╔╝    ██║███████╗
			   ██║   ██╔══██║██╔══╝      ██║███╗██║██║██║╚██╗██║██║╚██╗██║██╔══╝  ██╔══██╗    ██║╚════██║
			   ██║   ██║  ██║███████╗    ╚███╔███╔╝██║██║ ╚████║██║ ╚████║███████╗██║  ██║    ██║███████║
			   ╚═╝   ╚═╝  ╚═╝╚══════╝     ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝    ╚═╝╚══════╝
			                                                                                             
			 */
			//SEE DROOLS TO WINNER RULE
        	
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
        	tee.flush();
			tee.close();
		}
    }
}
