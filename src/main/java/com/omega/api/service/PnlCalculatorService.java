package com.omega.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.omega.api.dto.PnlInput;
import com.omega.api.dto.PnlOutput;
import com.omega.api.dto.Trade;
import com.omega.api.dto.Trade.TradeType;

@Service
public class PnlCalculatorService {
	
	/* PNL Calc Service, takes JSON input of transactions ( pre defined format) and calculates Cash, PNL & Holdings from JSON transactions
	 returns pnl details in JSON format*/

	public List<PnlOutput> calculate(PnlInput pnlInput) {

		List<PnlOutput> pnlOutputs = new ArrayList<>();
		// Get Total Cash
		Double cash = pnlInput.getBase().getCash();
		//Get Initial Holdings
		Map<String, Integer> holdings = pnlInput.getBase().getHoldings();
		
		ListMultimap<String, Trade> tradeMap = ArrayListMultimap.create();
		//Map<String, List<Trade>> tradeMap = new HashMap<>();
		Double pnl = 0.0;
		for (Trade trade : pnlInput.getTransactions()) {
			if (trade.getType().equals(TradeType.Buy)) {
				
				Integer newHoldingValue = holdings.get(trade.getSymbol()) + trade.getAmount();
				  // Adding current Quantity to holdings map for this security 
				holdings.put(trade.getSymbol(), newHoldingValue);
				 // Buy side so Net Money is deducted from current cash
				cash = cash - (trade.getAmount() * trade.getPrice());
				// MultiMap holds all buy transactions in the order of transactions
				tradeMap.put(trade.getSymbol(), trade);
				// Add this Transaction Pnl, Cash & holdings into a List
				PnlOutput pnlOutput = new PnlOutput(cash, pnl, deepCopy(holdings) );
				pnlOutputs.add(pnlOutput);
			} else {
				 // If the trade type is sell handle sell side logic
				// Substracting current Quantity to holdings map for this security 
				Integer newHoldingValue = holdings.get(trade.getSymbol()) - trade.getAmount();
				holdings.put(trade.getSymbol(), newHoldingValue);
				 // Sell side so Net Money is added to current cash
				cash = cash + (trade.getAmount() * trade.getPrice());
				 // Calculate Pnl on this sell transaction
				pnl += calculatePnl(tradeMap, trade);
				PnlOutput pnlOutput = new PnlOutput(cash, pnl, deepCopy(holdings));
				// // Add this Transaction Pnl, Cash & holdings into a List
				pnlOutputs.add(pnlOutput);
			}
		}
		return pnlOutputs;

	}
	/* Function calculates takes buySide Transaction Map and current transaction and returns pnl */
	private Double calculatePnl(ListMultimap<String, Trade> tradeMap, Trade trade) {
		List<Trade> buyTrades = tradeMap.get(trade.getSymbol());
		Double pnl = 0.0;
		for( Trade buyTrade : buyTrades ) {
			 /* Implement FIFO, if buy trade has equal or more quantity than current trade 
            then pnl is sell side netmoney minus trade side netmoney and deduct this trade quantity
            from the buy side transaction quantity.
            */
			if(buyTrade.getAmount() >= trade.getAmount() ) {
				pnl +=  trade.getPrice() * trade.getAmount() - buyTrade.getPrice() * trade.getAmount() ;
				buyTrade.setAmount( buyTrade.getAmount() - trade.getAmount() );
				return pnl; 
			} else {
				/*else calculate pnl for the remaining quantity in this transaction and return, so that in the 
                next next buy trade for this security remaining quantity can be used to calculate total pnl
                */
				pnl = trade.getPrice() * buyTrade.getAmount() - buyTrade.getPrice() * buyTrade.getAmount() ;
				trade.setAmount( trade.getAmount() - buyTrade.getAmount() );
			}
		}
		return pnl;
	}
	
	/* Util Method for Deep copy of a Map */
	private static Map<String, Integer> deepCopy(Map<String, Integer> originalMap) {
		Map<String, Integer> newMap = new HashMap<>();
		for (Map.Entry<String, Integer> entry : originalMap.entrySet()) {
			newMap.put(entry.getKey(), entry.getValue());
		}
		return newMap;
	}

}
