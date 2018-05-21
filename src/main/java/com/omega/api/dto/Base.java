package com.omega.api.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Base {
	
	private Double Cash;
	private Map<String, Integer> Holdings;
	
	@JsonProperty("Cash")
	public Double getCash() {
		return Cash;
	}
	@JsonProperty("Cash")
	public void setCash(Double Cash) {
		this.Cash = Cash;
	}
	@JsonProperty("Holdings")
	public Map<String, Integer> getHoldings() {
		return Holdings;
	}
	@JsonProperty("Holdings")
	public void setHoldings(Map<String, Integer> Holdings) {
		this.Holdings = Holdings;
	}

}
