package com.finance.preprocessor.utilities.currencyreaderextractor;

import com.finance.preprocessor.CurrencyFile;
import com.finance.preprocessor.utilities.currencyreaderextractor.utilities.*;
import com.finance.preprocessor.utilities.CurrencyPairDataPack;
import com.finance.preprocessor.utilities.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CurrencyReaderExtractor {

    private Logger logger = Logger.getLogger(CurrencyReaderExtractor.class.getName());

    @Autowired
    private CsvReader csvReader;
    @Autowired
    private DataPointExtractor dataPointExtractor;
    @Autowired
    private TimeFrameExtractor timeFrameExtractor;
    @Autowired
    private GapFiller gapFiller;
    @Autowired
    private FilePathExtractor pathExtractor;

    public List<CurrencyPairDataPack> readAndProcess(List<CurrencyFile> files, ChronoUnit requiredOutputTimeFrame,
                                                     ChronoUnit inputTimeFrame) {

        List<CurrencyPairDataPack> currencyPairDataPacks = new ArrayList<>();

        for (CurrencyFile currencyFile : files) {
            currencyPairDataPacks.add(extractCurrencyPairDataFromFolder(currencyFile, requiredOutputTimeFrame, inputTimeFrame));
        }

        return currencyPairDataPacks;
    }

    private CurrencyPairDataPack extractCurrencyPairDataFromFolder(
            CurrencyFile currencyFile, ChronoUnit requiredOutputTimeFrame, ChronoUnit inputTimeFrame){

        List<DataPoint> extractedDataPoints = null;

        List<String> pathsToFiles = pathExtractor.extractPathsToFilesInFolder(currencyFile.getFolderPath());
        if(pathsToFiles != null)
            extractedDataPoints = extractAndProcessDataPoints(pathsToFiles, requiredOutputTimeFrame, inputTimeFrame);
        else {
            logger.log(Level.SEVERE, "Skipping CurrencyFile: " + currencyFile.getPairName());
        }

        return new CurrencyPairDataPack(currencyFile.getPairName(), requiredOutputTimeFrame, extractedDataPoints);
    }

    private List<DataPoint> extractAndProcessDataPoints(List<String> pathsToFiles,
                                                        ChronoUnit requiredOutputTimeFrame, ChronoUnit inputTimeFrame) {
        List<DataPoint> dataPoints = new ArrayList<>();
        for(String path : pathsToFiles){
            List<List<String>> output = csvReader.read(path);
            dataPoints.addAll(dataPointExtractor.extract(output));
        }
        dataPoints = gapFiller.fill(dataPoints,inputTimeFrame);

        return timeFrameExtractor.extract(dataPoints, requiredOutputTimeFrame, inputTimeFrame);
    }
}
