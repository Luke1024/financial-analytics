package com.finance.preprocessor.utilities.currencyreaderextractor;
import com.finance.preprocessor.utilities.currencyreaderextractor.utilities.*;
import com.finance.preprocessor.utilities.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.temporal.ChronoUnit;
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

    public List<DataPoint> readAndProcess(File file, ChronoUnit requiredOutputTimeFrame, ChronoUnit inputTimeFrame) {

        logger.log(Level.INFO, "Reading file : " + file.getName());
        List<List<String>> output = csvReader.read(file);
        List<DataPoint> dataPoints = dataPointExtractor.extract(output);

        return timeFrameExtractor.extract(dataPoints, requiredOutputTimeFrame, inputTimeFrame);
    }


}
