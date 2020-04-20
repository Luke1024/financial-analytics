package com.finance.preprocessor.utilities.h1DataExtractorUtilities;

import java.time.temporal.ChronoUnit;

public class GapFillingStrategy {
    private int searchRangeForReplacing;
    private ChronoUnit usedForSearchRange;
    private boolean useAveraging;

    public GapFillingStrategy(int searchRangeForReplacing,
                              ChronoUnit usedForSearchRange,
                              boolean useAveraging) {
        this.searchRangeForReplacing = searchRangeForReplacing;
        this.usedForSearchRange = usedForSearchRange;
        this.useAveraging = useAveraging;
    }

    public int getSearchRangeForReplacing() {
        return searchRangeForReplacing;
    }

    public ChronoUnit getUsedForSearchRange() {
        return usedForSearchRange;
    }

    public boolean isUseAveraging() {
        return useAveraging;
    }
}
