package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            return Collections.emptyList();
        }
        return pathsToFiles;
    }

    public List<File> alternative(String folderPath){
        List<String> pathsToFiles = new ArrayList<>();
        ClassLoader classLoader = new FilePathExtractor().getClass().getClassLoader();
        File folder = new File(classLoader.getResource(folderPath).getFile());

        File[] files = folder.listFiles();
        if(files.length>0){
            return Arrays.asList(files);
        } else {
            logger.log(Level.SEVERE, "There is no files in folder!");
            return Collections.emptyList();
        }
    }
}
