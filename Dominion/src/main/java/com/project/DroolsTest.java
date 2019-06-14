package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	//Useful to the Gardens card
	public static Player actualPlayer;
	
	//To input
	public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
	
    public static final void main(String[] args) {
        try {
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	

        	/*
				███████╗███████╗████████╗██╗   ██╗██████╗ 
				██╔════╝██╔════╝╚══██╔══╝██║   ██║██╔══██╗
				███████╗█████╗     ██║   ██║   ██║██████╔╝
				╚════██║██╔══╝     ██║   ██║   ██║██╔═══╝ 
				███████║███████╗   ██║   ╚██████╔╝██║     
				╚══════╝╚══════╝   ╚═╝    ╚═════╝ ╚═╝                                    
        	 */

        	List<Player> players = new ArrayList<Player>();
        	players.add(new Player("Corrado"));
        	players.add(new Player("Vincenzo"));
        	players.add(new Player("Fulvio"));

        	//Randomly determine the starting player.
        	Collections.shuffle(players);
        	
        	
        	
        	//The setup is into the Table constructor
        	Table table = new Table();
        	//Initial cards distribution
        	table.getCardsToThePlayers(players);
        	
        	
        	kSession.setGlobal("players", players);
        	kSession.setGlobal("table", table);
        	
			     	
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
			kSession.setGlobal("actualPlayer", actualPlayer);
			
			do{
				System.out.println("Now it's the turn of "+actualPlayer.getUsername());
				
				/*
					 █████╗  ██████╗████████╗██╗ ██████╗ ███╗   ██╗    ██████╗ ██╗  ██╗ █████╗ ███████╗███████╗
					██╔══██╗██╔════╝╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔══██╗██║  ██║██╔══██╗██╔════╝██╔════╝
					███████║██║        ██║   ██║██║   ██║██╔██╗ ██║    ██████╔╝███████║███████║███████╗█████╗  
					██╔══██║██║        ██║   ██║██║   ██║██║╚██╗██║    ██╔═══╝ ██╔══██║██╔══██║╚════██║██╔══╝  
					██║  ██║╚██████╗   ██║   ██║╚██████╔╝██║ ╚████║    ██║     ██║  ██║██║  ██║███████║███████╗
					╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝    ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝
				 */
				System.out.println(Color.CYAN_BOLD);
				System.out.println(Color.BLACK_BACKGROUND);
				System.out.println("********************");
				System.out.println("****ACTION PHASE****");
				System.out.println("********************");
				System.out.println(Color.RESET);
				
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
				System.out.println(Color.CYAN_BOLD);
				System.out.println(Color.BLACK_BACKGROUND);
				System.out.println("*********************");
				System.out.println("******BUY PHASE******");
				System.out.println("*********************");
				System.out.println(Color.RESET);
				
				kSession.insert(Phase.PURCHASE);
				kSession.fireAllRules();
				
				
				
				/*
				 ██████╗██╗     ███████╗ █████╗ ███╗   ██╗      ██╗   ██╗██████╗     ██████╗ ██╗  ██╗ █████╗ ███████╗███████╗
				██╔════╝██║     ██╔════╝██╔══██╗████╗  ██║      ██║   ██║██╔══██╗    ██╔══██╗██║  ██║██╔══██╗██╔════╝██╔════╝
				██║     ██║     █████╗  ███████║██╔██╗ ██║█████╗██║   ██║██████╔╝    ██████╔╝███████║███████║███████╗█████╗  
				██║     ██║     ██╔══╝  ██╔══██║██║╚██╗██║╚════╝██║   ██║██╔═══╝     ██╔═══╝ ██╔══██║██╔══██║╚════██║██╔══╝  
				╚██████╗███████╗███████╗██║  ██║██║ ╚████║      ╚██████╔╝██║         ██║     ██║  ██║██║  ██║███████║███████╗
				 ╚═════╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝       ╚═════╝ ╚═╝         ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝
				 */
				
				System.out.println(Color.CYAN_BOLD);
				System.out.println(Color.BLACK_BACKGROUND);
				System.out.println("**************************");
				System.out.println("******CLEAN-UP PHASE******");
				System.out.println("**************************");
				System.out.println(Color.RESET);
				
				
				kSession.insert(Phase.CLEANUP);
				kSession.fireAllRules();
				
				
				kSession.insert(Phase.ENDTURN);
				// Check end game condition
				table.checkEndGame();
				
				
				//Switch the turn is moved into drools
				
			}while(table.getSituation() != 2);
			
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
        }
    }
    
    /*
     * OLD: User Input!
     * 
    public static int choiceActionPhase() throws NumberFormatException, IOException{
    	int scelta = -1;
		do{
			System.out.println(Color.CYAN_BACKGROUND_BRIGHT+actualPlayer.getUsername()+Color.RESET+" may chose a card Kingdom to play: ");
			System.out.println("   0 - Skip this phase");
			for(int j = 0; j < actualPlayer.getHand().size(); j++)
				// instead of putting an if else at the end of the loop we can check this before to avoid errors since the beginning
				if(actualPlayer.getHand().get(j) instanceof Kingdom)
					System.out.println("   "+(j+1)+" - "+actualPlayer.getHand().get(j));
			
			System.out.print(Color.BLUE_BOLD+"Option: "+Color.RESET);
			scelta = Integer.parseInt(console.readLine());
			
			
			if(scelta == 0)
				break;
			else if(scelta > 0 && scelta <= actualPlayer.getHand().size() &&!(actualPlayer.getHand().get(scelta-1) instanceof Kingdom))
				System.out.println(Color.RED_BRIGHT+"The chosen card is not a Kindom Card"+Color.RESET);
			else if(scelta > 0 && scelta <= actualPlayer.getHand().size() && (actualPlayer.getHand().get(scelta-1) instanceof Kingdom))
				break;
			else
				System.out.println(Color.RED_BRIGHT+"The option is not correct"+Color.RESET);
		}while(true);
		
		return scelta;
    }
    
    public static Card choiceBuyPhase(Table table) throws NumberFormatException, IOException{
    	Card chosenCard = null;
    	int scelta = -1;
    	int currentCoins = 0;
    	int currentPurchases = 0;
    	
    	
    	currentCoins = actualPlayer.getVirtualCoins();
    	currentPurchases = actualPlayer.getPurchases();
    	do{
			System.out.println(Color.CYAN_BACKGROUND_BRIGHT+actualPlayer.getUsername()+Color.RESET+" may chose a card to buy: coins("+actualPlayer.getVirtualCoins()+")");
			System.out.println("1 - Copper card (0)");
			System.out.println("2 - Silver card (3)");
			System.out.println("3 - Gold card (6)");
			System.out.println("4 - Estate card (2)");
			System.out.println("5 - Duchy card (5)");
			System.out.println("6 - Province card (8)");
			System.out.println("7 - Curse card (0)");
			System.out.println("8 - Kingdom card");
			
			System.out.print(Color.BLUE_BOLD+"Option: "+Color.RESET);
			scelta = Integer.parseInt(console.readLine());
			if(scelta < 0 || scelta > 8)
				System.out.println(Color.RED_BRIGHT+"Option is not valid, you mush choose a valid card!"+Color.RESET);
			else{				
				switch(scelta){
				// the card will be not null only if player can actually buy it
					case 1:
						chosenCard = actualPlayer.getCopperCard(table);
						break;
					case 2:
						chosenCard = actualPlayer.getSilverCard(table);
						break;
					case 3:
						chosenCard = actualPlayer.getGoldCard(table);
						break;
					case 4:
						chosenCard = actualPlayer.getEstateCard(table);
						break;
					case 5:
						chosenCard = actualPlayer.getDuchyCard(table);
						break;
					case 6:
						chosenCard = actualPlayer.getProvinceCard(table);
						break;
					case 7:
						chosenCard = actualPlayer.getCurseCard(table);
						break;
					case 8:
						//chosenCard = actualPlayer.getKingdomCard(table);
						break;
				}
				
				if(chosenCard == null){
					System.out.println(Color.RED_BRIGHT);
					System.out.println("The chosen deck on the table is empty or you can't afford that card");
					scelta = -1;
					System.out.println(Color.RESET);
				}
				else{
					//Questo dovrebbe essere actualPlayer.buy(...);
					if(actualPlayer.getVirtualCoins() < chosenCard.getCost()){
						System.out.println(Color.RED_BRIGHT);
						System.out.println(actualPlayer.getUsername()+" can't buy this card!");
						System.out.println(actualPlayer.getUsername()+" has "+actualPlayer.getVirtualCoins()+" coins!");
						System.out.println(chosenCard.getName() +" costs "+chosenCard.getCost()+ " coins!");
						scelta = -1;
						System.out.println(Color.RESET);
					}
					else{
						actualPlayer.getDiscard().add(chosenCard);
						actualPlayer.setPurchases(actualPlayer.getPurchases() - 1);
						//TODO YOU MUST DELETE COINS!!!!! I DON'T KNOW HOW TO DO THIS
						//FOR INSTANCE: IF I WANT TO BUY A CARD X (3 coins) AND I HAVE 2 SILVER (4 coins)
						//HOW CAN I EXECUTE THIS PROCEDURE? I DISCARD 2 SILVER AND DRAW 1 COPPER? 

						System.out.println(Color.RESET);
						System.out.println(actualPlayer.getUsername()+ " bought this card: "+chosenCard);
						System.out.println("Now "+actualPlayer.getUsername()+ " has "+actualPlayer.getVirtualCoins()+ " coins");
					}
					
				}
			}
		}while(scelta < 0 || scelta > 8);
    	return chosenCard;
    }

	*/
}
