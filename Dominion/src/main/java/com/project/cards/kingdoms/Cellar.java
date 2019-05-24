package com.project.cards.kingdoms;

public class Cellar extends Kingdom {

	private int amount;
	
	public Cellar() {
		super("Cellar", null, 2);
		this.amount = 0;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
