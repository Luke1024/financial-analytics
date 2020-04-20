package com.finance.preprocessor.utilities;

import com.finance.preprocessor.utilities.h1DataExtractorUtilities.GapAnalyzer;
import com.finance.preprocessor.utilities.h1DataExtractorUtilities.GapFiller;
import com.finance.preprocessor.utilities.h1DataExtractorUtilities.GapFillingStrategy;
import com.finance.preprocessor.utilities.h1DataExtractorUtilities.GapReport;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class H1DataExtractor {

    private GapAnalyzer gapAnalyzer = new GapAnalyzer();
    private GapFiller gapFiller = new GapFiller();
    private int gapCount = 0;

    public List<DataPoint> extract(List<DataPoint> dataPoints) {

        List<Integer> indexes = findIndexesMatchingH1TimeFrame(dataPoints);

        GapReport gapReport = gapAnalyzer.analyzeGaps(indexes, dataPoints,
                ChronoUnit.HOURS, 1);

        List<DataPoint> h1values;

        if(gapReport.isGapDetected()){
            h1values = gapFiller.fill(dataPoints, indexes, gapReport,
                    new GapFillingStrategy(30, ChronoUnit.MINUTES, true));
        } else {
            h1values = getValuesOnMatchingIndexes(dataPoints, indexes);
        }
        return h1values;
    }

    private List<Integer> findIndexesMatchingH1TimeFrame(List<DataPoint> dataPoints){
        List<Integer> indexes = new ArrayList<>();
        for(int i=0; i<dataPoints.size(); i++) {
            if(dataPoints.get(i).getLocalDateTime().getMinute() == 0) indexes.add(i);
        }
        return indexes;
    }

    private List<DataPoint> getValuesOnMatchingIndexes(List<DataPoint> dataPoints, List<Integer> indexes){
        List<DataPoint> h1DataPoints = new ArrayList<>();
        for(Integer index : indexes){
            h1DataPoints.add(dataPoints.get(index));
        }
        return h1DataPoints;
    }
}
