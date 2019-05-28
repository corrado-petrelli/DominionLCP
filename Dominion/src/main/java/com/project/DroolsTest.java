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
        	List<Kingdom> allKingdomCard = new ArrayList<>();
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
			//12 gardens cards
			for (j = 0; j < 12; j++){
				allKingdomCard.add(new Gardens());
			}

			*/
        	

        	/*
				███████╗███████╗████████╗██╗   ██╗██████╗ 
				██╔════╝██╔════╝╚══██╔══╝██║   ██║██╔══██╗
				███████╗█████╗     ██║   ██║   ██║██████╔╝
				╚════██║██╔══╝     ██║   ██║   ██║██╔═══╝ 
				███████║███████╗   ██║   ╚██████╔╝██║     
				╚══════╝╚══════╝   ╚═╝    ╚═════╝ ╚═╝                                    
        	 */
        	int j = 0;
        	List<Player> players = new ArrayList<Player>();
        	players.add(new Player("Corrado"));
        	players.add(new Player("Vincenzo"));
        	players.add(new Player("Fulvio"));
        	
        	//The setup is into the Table constructor
        	Table table = new Table();
        	//Initial cards distribution
        	table.getCardsToThePlayers(players);
        	
			     	
			/*
				██████╗ ██╗      █████╗ ██╗   ██╗
				██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝
				██████╔╝██║     ███████║ ╚████╔╝ 
				██╔═══╝ ██║     ██╔══██║  ╚██╔╝  
				██║     ███████╗██║  ██║   ██║   
				╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   				                                 
			 */
			//Randomly determine the starting player.
        	Collections.shuffle(players);
			int indexActualPlayer = 0;
			actualPlayer = players.get(indexActualPlayer);
			
			int numberOfRounds = 0;
			do{
				System.out.println("Now it's the turn of "+actualPlayer.getUsername());
				actualPlayer.setActions(1);
				actualPlayer.setPurchases(1);;
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
				
				int actualActions = 0;
				// Actual player plays a card from his/her hand
				do{
					int scelta = choiceActionPhase();
					
					//If the player chose a card delete it from his/her hand and play it
					if(scelta != 0){
						int indexOfChosenCard = scelta - 1;
						Card chosenCard = actualPlayer.getHand().get(indexOfChosenCard);
						System.out.println(actualPlayer.getUsername()+" chose "+chosenCard);
						kSession.insert(actualPlayer);
						kSession.insert(chosenCard);
						kSession.insert(Action.USE);
						kSession.fireAllRules();
					}
					else
						System.out.println(actualPlayer.getUsername()+ " skip this phase");
					
					actualActions++;
				}while(actualActions < actualPlayer.getActions());
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
				// Actual player buys a random card from one of the supplies
				int actualPurchases = 0;
				do{
					Card chosenCard = choiceBuyPhase(table);
					kSession.insert(actualPlayer);
					kSession.insert(chosenCard);
					kSession.insert(Action.BUY);
					kSession.fireAllRules();
					actualPurchases++;
				}while(actualPurchases < actualPlayer.getPurchases());
				
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
				
				System.out.println("TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				//Check here if 3 or more supply piles are empty
				
				
				
				
				
				//Change the turn
				if(indexActualPlayer == 2)
					indexActualPlayer = 0;
				else
					indexActualPlayer++;
				actualPlayer = players.get(indexActualPlayer);
				numberOfRounds++;
			}while(numberOfRounds < 100  /* provinceDeck.size() == 0 */ /*&& any 3 Supply piles are empty*/);
			
			/*
			████████╗██╗  ██╗███████╗    ██╗    ██╗██╗███╗   ██╗███╗   ██╗███████╗██████╗     ██╗███████╗
			╚══██╔══╝██║  ██║██╔════╝    ██║    ██║██║████╗  ██║████╗  ██║██╔════╝██╔══██╗    ██║██╔════╝
			   ██║   ███████║█████╗      ██║ █╗ ██║██║██╔██╗ ██║██╔██╗ ██║█████╗  ██████╔╝    ██║███████╗
			   ██║   ██╔══██║██╔══╝      ██║███╗██║██║██║╚██╗██║██║╚██╗██║██╔══╝  ██╔══██╗    ██║╚════██║
			   ██║   ██║  ██║███████╗    ╚███╔███╔╝██║██║ ╚████║██║ ╚████║███████╗██║  ██║    ██║███████║
			   ╚═╝   ╚═╝  ╚═╝╚══════╝     ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝    ╚═╝╚══════╝
			                                                                                             
			 */
			//Sort ascendent the players (the class player implements Comparable)
			Collections.sort(players);
			//Reverse the list
			Collections.reverse(players);
			//Print the ranking
			
			System.out.println("***RANK***");
			for (j = 0; j < players.size(); j++)
				System.out.println((j+1)+"-\t"+players.get(j));
			
        	//kSession.fireAllRules();
        	
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    
    public static int choiceActionPhase() throws NumberFormatException, IOException{
    	int scelta = -1;
		do{
			System.out.println(Color.CYAN_BACKGROUND_BRIGHT+actualPlayer.getUsername()+Color.RESET+" may chose a card Kingdom to play: ");
			System.out.println("   0 - Skip this phase");
			for(int j = 0; j < actualPlayer.getHand().size(); j++)
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
    	do{
			System.out.println(Color.CYAN_BACKGROUND_BRIGHT+actualPlayer.getUsername()+Color.RESET+" may chose a card to buy: ");
			System.out.println("1 - Copper card");
			System.out.println("2 - Silver card");
			System.out.println("3 - Gold card");
			System.out.println("4 - Estate card");
			System.out.println("5 - Duchy card");
			System.out.println("6 - Province card");
			System.out.println("7 - Curse card");
			System.out.println("8 - Kindom card");
			
			System.out.print(Color.BLUE_BOLD+"Option: "+Color.RESET);
			scelta = Integer.parseInt(console.readLine());
			if(scelta < 0 || scelta > 8)
				System.out.println(Color.RED_BRIGHT+"Option is not valid, you mush choose a valid card!"+Color.RESET);
			else{				
				switch(scelta){
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
						chosenCard = actualPlayer.getKingdomCard(table);
						break;
				}
				
				if(chosenCard == null){
					System.out.println(Color.RED_BRIGHT);
					System.out.println("The chosen deck on the table is empty");
					scelta = -1;
					System.out.println(Color.RESET);
				}
				else{
					//Questo dovrebbe essere actualPlayer.buy(...);
					if(actualPlayer.getTotalCoins() < chosenCard.getCost()){
						System.out.println(Color.RED_BRIGHT);
						System.out.println(actualPlayer.getUsername()+" can't buy this card!");
						System.out.println(actualPlayer.getUsername()+" has "+actualPlayer.getTotalCoins()+" coins!");
						System.out.println(chosenCard.getName() +" costs "+chosenCard.getCost()+ " coins!");
						scelta = -1;
						System.out.println(Color.RESET);
					}
					else{
						//TODO YOU MUST DELETE COINS!!!!! I DON'T KNOW HOW TO DO THIS
						//FOR INSTANCE: IF I WANT TO BUY A CARD X (3 coins) AND I HAVE 2 SILVER (4 coins)
						//HOW CAN I EXECUTE THIS PROCEDURE? I DISCARD 2 SILVER AND DRAW 1 COPPER? 

						System.out.println(Color.RESET);
						System.out.println(actualPlayer.getUsername()+ " bought this card: "+chosenCard);
						System.out.println("Now "+actualPlayer.getUsername()+ " has "+actualPlayer.getTotalCoins()+ " coins");
					}
					
				}
			}
		}while(scelta < 0 || scelta > 8);
    	return chosenCard;
    }


}
