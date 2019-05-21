package com.project.cards.victories;

import com.project.cards.IVictoryCard;

public class Province extends Victory implements IVictoryCard{

	public Province() {
		super("Province", 8, 6);
	}

	@Override
	public int getVictoryPoint() {
		return 6;
	}

}
