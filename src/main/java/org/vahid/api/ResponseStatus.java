package org.vahid.api;

/**
 * Created by vahid (@vahid_r)
 */

public class ResponseStatus {
    private String entity;
    private String title;
    private boolean successful;

    public ResponseStatus() {
    }

    public ResponseStatus(String entity, String title, boolean successful) {
        this.entity = entity;
        this.title = title;
        this.successful = successful;
    }

    public String getEntity() {
        return entity;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
