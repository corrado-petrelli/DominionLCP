package com.project.cards.victories;

import com.project.cards.IVictoryCard;

public class Duchy extends Victory implements IVictoryCard{

	public Duchy() {
		super("Duchy", 5, 3);
		
	}

	@Override
	public int getVictoryPoint() {
		return 3;
	}
	
}
