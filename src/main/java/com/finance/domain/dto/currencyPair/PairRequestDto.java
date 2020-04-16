package com.finance.domain.dto.currencyPair;

import java.util.List;

public class PairRequestDto {
    private List<String> pairName;
    private ViewTimeFrame viewTimeFrame;

    public PairRequestDto(List<String> pairName, ViewTimeFrame viewTimeFrame) {
        this.pairName = pairName;
        this.viewTimeFrame = viewTimeFrame;
    }
}
