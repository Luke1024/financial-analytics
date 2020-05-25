package com.finance.service.database.communicationObjects;

import java.util.List;

public class DatabaseResponse {
    private List<DatabaseEntity> requestedObjects;
    private String log;
    private boolean OK;

    public DatabaseResponse(List<DatabaseEntity> requestedObjects, String log, boolean OK) {
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
