package com.omega.api.dto;

import java.util.List;

public class PnlInput {
	
	private Base base;
	private List<Trade> transactions;
	
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	public List<Trade> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Trade> transactions) {
		this.transactions = transactions;
	}

}
