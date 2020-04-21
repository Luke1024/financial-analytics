package com.finance.preprocessor;

public class CurrencyFile {
    private String pairName;
    private String folderPath;

    public CurrencyFile(String pairName, String folderPath) {
        this.pairName = pairName;
        this.folderPath = folderPath;
    }

    public String getPairName() {
        return pairName;
    }

    public String getFolderPath() {
        return folderPath;
    }
}
