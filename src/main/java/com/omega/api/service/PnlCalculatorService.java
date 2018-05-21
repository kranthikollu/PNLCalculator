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

	public List<PnlOutput> calculate(PnlInput pnlInput) {

		List<PnlOutput> pnlOutputs = new ArrayList<>();
		Double cash = pnlInput.getBase().getCash();
		Map<String, Integer> holdings = pnlInput.getBase().getHoldings();
		ListMultimap<String, Trade> tradeMap = ArrayListMultimap.create();
		//Map<String, List<Trade>> tradeMap = new HashMap<>();
		Double pnl = 0.0;
		for (Trade trade : pnlInput.getTransactions()) {
			if (trade.getType().equals(TradeType.Buy)) {
				Integer newHoldingValue = holdings.get(trade.getSymbol()) + trade.getAmount();
				holdings.put(trade.getSymbol(), newHoldingValue);
				cash = cash - (trade.getAmount() * trade.getPrice());
				tradeMap.put(trade.getSymbol(), trade);
				PnlOutput pnlOutput = new PnlOutput(cash, pnl, deepCopy(holdings) );
				pnlOutputs.add(pnlOutput);
			} else {
				Integer newHoldingValue = holdings.get(trade.getSymbol()) - trade.getAmount();
				holdings.put(trade.getSymbol(), newHoldingValue);
				cash = cash + (trade.getAmount() * trade.getPrice());
				pnl += calculatePnl(tradeMap, trade);
				PnlOutput pnlOutput = new PnlOutput(cash, pnl, deepCopy(holdings));
				pnlOutputs.add(pnlOutput);
			}
		}
		return pnlOutputs;

	}

	private Double calculatePnl(ListMultimap<String, Trade> tradeMap, Trade trade) {
		List<Trade> buyTrades = tradeMap.get(trade.getSymbol());
		Double pnl = 0.0;
		for( Trade buyTrade : buyTrades ) {
			if(buyTrade.getAmount() >= trade.getAmount() ) {
				pnl +=  trade.getPrice() * trade.getAmount() - buyTrade.getPrice() * trade.getAmount() ;
				buyTrade.setAmount( buyTrade.getAmount() - trade.getAmount() );
				return pnl; 
			} else {
				pnl = trade.getPrice() * buyTrade.getAmount() - buyTrade.getPrice() * buyTrade.getAmount() ;
				trade.setAmount( trade.getAmount() - buyTrade.getAmount() );
			}
		}
		return pnl;
	}

	private static Map<String, Integer> deepCopy(Map<String, Integer> originalMap) {
		Map<String, Integer> newMap = new HashMap<>();
		for (Map.Entry<String, Integer> entry : originalMap.entrySet()) {
			newMap.put(entry.getKey(), entry.getValue());
		}
		return newMap;
	}

}
