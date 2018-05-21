package com.omega.api.dto;

import java.util.Map;

public class PnlOutput {
	
	private Double cash;
	private Double pnl;
	private Map<String, Integer> holdings;

	public PnlOutput( Double cash, Double pnl, Map<String, Integer> holdings) {
		this.pnl = pnl;
		this.cash = cash;
		this.holdings = holdings;
	}
	
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getPnl() {
		return pnl;
	}

	public void setPnl(Double pnl) {
		this.pnl = pnl;
	}

	public Map<String, Integer> getHoldings() {
		return holdings;
	}

	public void setHoldings(Map<String, Integer> holdings) {
		this.holdings = holdings;
	}

}
