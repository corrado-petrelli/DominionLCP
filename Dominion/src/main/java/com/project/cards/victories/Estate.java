package com.project.cards.victories;

import com.project.cards.IVictoryCard;

public class Estate extends Victory implements IVictoryCard{

	public Estate() {
		super("Estate",2,1);
	}

	@Override
	public int getVictoryPoint() {
		return 1;
	}

}
