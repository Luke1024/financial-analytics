package com.finance.preprocessor.utilities;

import com.finance.preprocessor.CurrencyFile;
import com.finance.preprocessor.utilities.timeFrameDataExtractor.GapFiller;
import com.finance.preprocessor.utilities.timeFrameDataExtractor.TimeFrameExtractor;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CurrencyReaderExtractor {

    private CsvReader csvReader = new CsvReader();
    private DataPointExtractor dataPointExtractor = new DataPointExtractor();
    private TimeFrameExtractor timeFrameExtractor = new TimeFrameExtractor();
    private GapFiller gapFiller = new GapFiller();


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

        File folder = new File(currencyFile.getFolderPath());

        List<String> pathsToFiles = new ArrayList<>();
        for(File fileEntry : folder.listFiles()){
            pathsToFiles.add(fileEntry.getName());
        }

        List<DataPoint> extractedDataPoints = extractAndProcessDataPoints(pathsToFiles, requiredOutputTimeFrame, inputTimeFrame);

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
