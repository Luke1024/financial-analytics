package com.finance.preprocessor;

public class CurrencyFile {
    private String fileName;
    private String folderPath;

    public CurrencyFile(String fileName, String folderPath) {
        this.fileName = fileName;
        this.folderPath = folderPath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFolderPath() {
        return folderPath;
    }
}
