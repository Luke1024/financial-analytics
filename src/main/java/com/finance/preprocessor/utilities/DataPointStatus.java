package com.finance.preprocessor.utilities;

import java.util.List;

public class DataPointStatus {
    private List<String> elementName;
    private List<Boolean> status;

    public DataPointStatus(List<String> elementName, List<Boolean> status) {
        this.elementName = elementName;
        this.status = status;
    }

    public List<String> getElementName() {
        return elementName;
    }

    public List<Boolean> getStatus() {
        return status;
    }
}
