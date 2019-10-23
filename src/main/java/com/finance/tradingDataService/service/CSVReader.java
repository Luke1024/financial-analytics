package com.finance.tradingDataService.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReader {
    public List<String> readCsv(String name) throws Exception {
        List<String> symbols = new ArrayList<>();

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(name));
            while ((line = br.readLine()) != null) {

                String[] instrument = line.split(cvsSplitBy);
                symbols.add(instrument[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return symbols;
    }
}
