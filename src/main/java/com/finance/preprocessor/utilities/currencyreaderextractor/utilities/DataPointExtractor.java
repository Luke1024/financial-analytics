package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import com.finance.preprocessor.utilities.DataPoint;
import com.finance.preprocessor.utilities.currencyreaderextractor.utilities.datapointextractorutilities.Analysis;
import com.finance.preprocessor.utilities.currencyreaderextractor.utilities.datapointextractorutilities.RowAnalyzer;
import com.finance.preprocessor.utilities.currencyreaderextractor.utilities.datapointextractorutilities.RowParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DataPointExtractor {

    private Logger logger = Logger.getLogger(DataPointExtractor.class.getName());

    @Autowired
    private RowAnalyzer rowAnalyzer;
    @Autowired
    private RowParser rowParser;

    public List<DataPoint> extract(List<List<String>> input){
        List<DataPoint> dataPoints = new ArrayList<>();

        int problematicRowCounter = 0;

        for(List row : input){
            Analysis analysis = rowAnalyzer.analyze(row);
            if(analysis.isRowCorrect()) {
                DataPoint dataPoint = rowParser.parse(row);
                if(dataPoint != null){
                    dataPoints.add(dataPoint);
                } else {
                    problematicRowCounter += 1;
                }
            }
        }

        if(problematicRowCounter > 0){
            logger.log(Level.INFO, "Parsing problem detected in " + problematicRowCounter + " rows.");
        }

        return dataPoints;
    }
}
