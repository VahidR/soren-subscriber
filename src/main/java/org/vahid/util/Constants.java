package org.vahid.util;

/**
 * Created by vahid (@vahid_r)
 */

public enum Constants {
    APPLICATION_NAME("SorenSubscriber"),
    DATABASE_TYPE("postgresql"),
    RESOURCE_NOT_OK("Could not create the resource!"),
    CATEGORY_ENTITY("category"),
    BOOK_ENTITY("book"),
    SUBSCRIBER_ENTITY("subscriber"),
    HEALTHY_MESSAGE("Soren Subscriber is healthy, up and running.."),
    UNHEALTHY_MESSAGE("Soren Subscriber is not healthy!");

    public String value;

    Constants(String value) {
        this.value = value;
    }
}

