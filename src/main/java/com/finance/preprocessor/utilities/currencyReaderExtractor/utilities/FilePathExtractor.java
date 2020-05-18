package com.finance.preprocessor.utilities.currencyReaderExtractor.utilities;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FilePathExtractor {

    private Logger logger = Logger.getLogger(FilePathExtractor.class.getName());

    public List<String> extractPathsToFilesInFolder(String folderPath){
        List<String> pathsToFiles = new ArrayList<>();
        File folder = new File(folderPath);

        File[] files = folder.listFiles();
        if(files.length > 0){
            for(int i = 0; i<files.length; i++) {
                pathsToFiles.add(folderPath + "\\" + files[i].getName());
            }
        } else {
            logger.log(Level.SEVERE, "There is no files in folder!");
            return null;
        }
        return pathsToFiles;
    }
}
