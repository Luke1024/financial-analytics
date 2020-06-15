package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import com.finance.preprocessor.utilities.DataPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GapFillerTest {

    @Autowired
    private GapFiller gapFiller;

    private LocalDateTime date1 = LocalDateTime.of(2020,5,16,15,0);
    private LocalDateTime date2 = LocalDateTime.of(2020,5,16,16,0);
    private LocalDateTime date3 = LocalDateTime.of(2020,5,16,17,0);
    private LocalDateTime date4 = LocalDateTime.of(2020,5,16,18,0);
    private LocalDateTime date5 = LocalDateTime.of(2020,5,16,19,0);
    private LocalDateTime date6 = LocalDateTime.of(2020, 5, 16, 20,0);
    private LocalDateTime date7 = LocalDateTime.of(2020,5,16,21,0);

    private DataPoint dataPoint1 = new DataPoint(date1, 2.5);
    private DataPoint dataPoint2 = new DataPoint(date2, 3.0);
    private DataPoint dataPoint3 = new DataPoint(date3, 3.5);
    private DataPoint dataPoint4 = new DataPoint(date4, 4.0);
    private DataPoint dataPoint5 = new DataPoint(date5, 4.5);
    private DataPoint dataPoint6 = new DataPoint(date6, 5.0);
    private DataPoint dataPoint7 = new DataPoint(date7, 5.5);


    @Test
    public void testGapFillingSingleGap(){
        List<DataPoint> dataPointsWithGap = new ArrayList<>(Arrays.asList(dataPoint1, dataPoint7));
        List<DataPoint> dataPointsExpected = new ArrayList<>(Arrays.asList(dataPoint1, dataPoint2, dataPoint3, dataPoint4, dataPoint5, dataPoint6, dataPoint7));

        List<DataPoint> dataPointsWithFilledGap = gapFiller.fill(dataPointsWithGap, ChronoUnit.HOURS);

        Assert.assertEquals(dataPointsExpected.toString(), dataPointsWithFilledGap.toString());
    }

    @Test
    public void testFillingComplexGap(){
        List<DataPoint> dataPointsWithComplexGap = new ArrayList<>(Arrays.asList(dataPoint1, dataPoint3, dataPoint4, dataPoint7));
        List<DataPoint> dataPointsExpected = new ArrayList<>(Arrays.asList(dataPoint1, dataPoint2, dataPoint3, dataPoint4, dataPoint5, dataPoint6, dataPoint7));

        List<DataPoint> dataPointsWithFilledGap = gapFiller.fill(dataPointsWithComplexGap, ChronoUnit.HOURS);

        Assert.assertEquals(dataPointsExpected.toString(), dataPointsWithFilledGap.toString());
    }

    @Test
    public void testFillingComplexGap2(){
        List<DataPoint> dataPointsWithComplexGap = new ArrayList<>(Arrays.asList(dataPoint1, dataPoint3, dataPoint5, dataPoint7));
        List<DataPoint> dataPointsExpected = new ArrayList<>(Arrays.asList(dataPoint1, dataPoint2, dataPoint3, dataPoint4, dataPoint5, dataPoint6, dataPoint7));

        List<DataPoint> dataPointsWithFilledGap = gapFiller.fill(dataPointsWithComplexGap, ChronoUnit.HOURS);

        Assert.assertEquals(dataPointsExpected.toString(), dataPointsWithFilledGap.toString());
    }

    @Test
    public void testGapFillingDatasetEmty(){
        List<DataPoint> dataPoints = new ArrayList<>();

        List<DataPoint> expectedEmpty = gapFiller.fill(dataPoints, ChronoUnit.HOURS);


        Assert.assertEquals(dataPoints, expectedEmpty);
    }
}