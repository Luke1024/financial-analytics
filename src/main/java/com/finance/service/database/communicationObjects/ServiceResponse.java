package com.finance.service.database.communicationObjects;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse {
    private List<DatabaseEntity> requestedObjects = new ArrayList<>();
    private String log;
    private boolean OK;

    public ServiceResponse(List<DatabaseEntity> requestedObjects, String log, boolean OK) {
        this.requestedObjects = requestedObjects;
        this.log = log;
        this.OK = OK;
    }

    public List<DatabaseEntity> getRequestedObjects() {
        return requestedObjects;
    }

    public String getLog() {
        return log;
    }

    public boolean isOK() {
        return OK;
    }
}
