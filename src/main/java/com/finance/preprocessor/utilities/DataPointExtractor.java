package com.finance.preprocessor.utilities;

import com.finance.preprocessor.utilities.dataPointExtractorUtilities.Analysis;
import com.finance.preprocessor.utilities.dataPointExtractorUtilities.RowAnalyzer;
import com.finance.preprocessor.utilities.dataPointExtractorUtilities.RowParser;

import java.util.ArrayList;
import java.util.List;

public class DataPointExtractor {

    private List<DataPointStatus> dataPointStatusList = new ArrayList<>();

    private RowAnalyzer rowAnalyzer = new RowAnalyzer();
    private RowParser rowParser = new RowParser();

    public List<DataPoint> extract(List<List<String>> input){
        List<DataPoint> dataPoints = new ArrayList<>();

        for(List row : input){
            Analysis analysis = rowAnalyzer.analyze(row);
            if(analysis.isRowCorrect()) {
                dataPoints.add(rowParser.parse(row));
            }
            dataPointStatusList.add(analysis.getRaport());
        }
        return dataPoints;
    }
}
