package com.finance.preprocessor.utilities;

import java.util.ArrayList;
import java.util.List;

public class DataPointPack {
    private List<DataPoint> dataPointList = new ArrayList<>();

    public DataPointPack(List<DataPoint> dataPointList) {
        this.dataPointList = dataPointList;
    }

    public List<DataPoint> getDataPointList() {
        return dataPointList;
    }
}
