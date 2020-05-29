package com.finance.domain.dto.currencyPair;

import java.util.ArrayList;
import java.util.List;

public class DataPointDtoPack {
    private List<DataPointDto> dataPointDtoList;

    public DataPointDtoPack(List<DataPointDto> dataPointDtoList) {
        this.dataPointDtoList = dataPointDtoList;
    }

    public List<DataPointDto> getDataPointDtoList() {
        return dataPointDtoList;
    }
}
