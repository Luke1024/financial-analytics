package com.finance.domain.dto.currencyPair;

public class PairRequestDto {
    private String pairName;
    private ViewTimeFrame viewTimeFrame;

    public PairRequestDto(String pairName, ViewTimeFrame viewTimeFrame) {
        this.pairName = pairName;
        this.viewTimeFrame = viewTimeFrame;
    }

    public String getPairName() {
        return pairName;
    }

    public ViewTimeFrame getViewTimeFrame() {
        return viewTimeFrame;
    }
}
