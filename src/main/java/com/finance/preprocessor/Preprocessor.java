package com.finance.preprocessor;

import com.finance.preprocessor.utilities.CsvReader;
import com.finance.preprocessor.utilities.DataPoint;
import com.finance.preprocessor.utilities.DataPointExtractor;
import com.finance.preprocessor.utilities.H1DataExtractor;

import java.util.ArrayList;
import java.util.List;

public class Preprocessor {
    private CsvReader csvReader = new CsvReader();
    private DataPointExtractor dataPointExtractor = new DataPointExtractor();
    private H1DataExtractor h1DataExtractor = new H1DataExtractor();
    //private DataBaseLoader dataBaseLoader = new DataBaseLoader();

    private final String fileName = "/home/luke/trading_app_april/src/main/java/com/finance/" +
            "preprocessor/data/eurusd/DAT_MT_EURUSD_M1_2000.csv";

    public void loadDataIntoDatabase() {
        List<DataPoint> dataPoints = new ArrayList<>();
        
        List<List<String>> output = csvReader.read(fileName);
        dataPoints.addAll(dataPointExtractor.extract(output));

        List<DataPoint> h1DataPoints = h1DataExtractor.extract(dataPoints);
    }
}
