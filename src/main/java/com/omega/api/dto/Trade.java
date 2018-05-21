package com.omega.api.dto;

public class Trade implements Comparable<Trade>{
	
	public enum TradeType{Buy, Sell};
	
	private String symbol;
	private TradeType type;
	private Integer amount;
	private Double price;
	private String date;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public int compareTo(Trade o) {
		if(this.date == null) {
			return 1;
		}
		if(o.date == null) {
			return 1;
		}
		return this.date.compareTo(o.getDate());
	}
	
	@Override
	public String toString() {
		return "Trade [symbol=" + symbol + ", type=" + type + ", amount=" + amount + ", price=" + price + ", date="
				+ date + "]";
	}

}
