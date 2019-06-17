package com.project.cards.kingdoms;

import com.project.DroolsTest;
import com.project.cards.IVictoryCard;

public class Gardens extends Kingdom implements IVictoryCard{

	public Gardens() {
		super("Gardens", "Victory", 4);
		
	}

	/**
	 * Return 1 if the actual player has at least 10 cards
	 * else 0 points
	 */
	@Override
	public int getVictoryPoint() {
		int point = DroolsTest.actualPlayer.getDeck().size() % 10;
		if(DroolsTest.actualPlayer.getDeck().size() >= 10)
			return (int) Math.ceil(DroolsTest.actualPlayer.getDeck().size() / 10);
		else
			return 0;
	}
	
	
}
