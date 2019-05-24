package com.project.cards.kingdoms;

public class Chapel extends Kingdom {
	
	private int amount;
	
	public int getAmount() {
		return amount;
	}

	public Chapel() {
		super("Chapel", null, 2);
		
		this.amount = 0;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


}
