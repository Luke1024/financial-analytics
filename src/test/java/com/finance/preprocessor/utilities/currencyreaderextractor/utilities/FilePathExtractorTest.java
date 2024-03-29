package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilePathExtractorTest {

    @Autowired
    private FilePathExtractor pathExtractor;

    @Test
    @Ignore
    public void testExtractingPathsFromFolder(){
        //String path = "C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\test\\java\\com\\finance\\preprocessor\\eurusd";

        //List<String> pathsToFiles = pathExtractor.extractPathsToFilesInFolder(path);

        //String expectedPath = "C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\test\\java\\com\\finance\\preprocessor\\eurusd\\DAT_MT_EURUSD_M1_2000.csv";

        //Assert.assertEquals(expectedPath, pathsToFiles.get(0));
    }

    @Test
    public void testAlternativeMethod(){
        //List<File> files = pathExtractor.alternative("data/eurusd");
        //for(File file : files){
            //System.out.println(file.getName());
        //}
    }
}