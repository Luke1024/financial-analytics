package com.finance.preprocessor.utilities.currencyReaderExtractor;

import com.finance.preprocessor.CurrencyFile;
import com.finance.preprocessor.utilities.currencyReaderExtractor.utilities.*;
import com.finance.preprocessor.utilities.CurrencyPairDataPack;
import com.finance.preprocessor.utilities.DataPoint;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyReaderExtractor {

    Logger logger = Logger.getLogger(CurrencyReaderExtractor.class.getName());

    private CsvReader csvReader = new CsvReader();
    private DataPointExtractor dataPointExtractor = new DataPointExtractor();
    private TimeFrameExtractor timeFrameExtractor = new TimeFrameExtractor();
    private GapFiller gapFiller = new GapFiller();
    private FilePathExtractor pathExtractor = new FilePathExtractor();

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
            logger.log(Level.SEVERE, "Skipping CurrencyFile.");
        }

        return new CurrencyPairDataPack(currencyFile.getPairName(), requiredOutputTimeFrame, extractedDataPoints);
    }

    private List<String> getPathsToFilesInFolder(String folderPath){
        List<String> pathsToFiles = new ArrayList<>();
        File folder = new File(folderPath);
        List<File> files = Arrays.asList(folder.listFiles());
        if(files.size()>0 && folder.listFiles() == null){
            for(File fileEntry : files) {
                pathsToFiles.add(fileEntry.getName());
            }
        } else {
            logger.log(Level.SEVERE, "There is no files in folder!");
            return null;
        }
        return pathsToFiles;
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
