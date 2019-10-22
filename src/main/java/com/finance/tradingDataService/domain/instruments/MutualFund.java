package com.finance.tradingDataService.domain.instruments;

import java.util.List;

@Entity
public class MutualFund {
    private String symbol;
    private String name;
    private List<MutualFund> mutualFundHistoryPoint;

    public MutualFund(String symbol, String name, List<MutualFund> mutualFundHistoryPoint) {
        this.symbol = symbol;
        this.name = name;
        this.mutualFundHistoryPoint = mutualFundHistoryPoint;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public List<MutualFund> getMutualFundHistoryPoint() {
        return mutualFundHistoryPoint;
    }
}
