package com.finance.service.database.communicationObjects;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse {
    private List<DatabaseEntity> requestedObjects = new ArrayList<>();
    private String log;

    public ServiceResponse(List<DatabaseEntity> requestedObjects, String log) {
        this.requestedObjects = requestedObjects;
        this.log = log;
    }

    public List<DatabaseEntity> getRequestedObjects() {
        return requestedObjects;
    }

    public String getLog() {
        return log;
    }
}
