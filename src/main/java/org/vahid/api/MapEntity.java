package org.vahid.api;

import com.google.common.base.MoreObjects;

/**
 * Created by vahid (@vahid_r)
 */

public class MapEntity {
    private String email;
    private String categoryCodes;

    public MapEntity() {
    }

    public MapEntity(String email, String categoryCodes) {
        this.email = email;
        this.categoryCodes = categoryCodes;
    }

    public String getEmail() {
        return email;
    }

    public String getCategoryCodes() {
        return categoryCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapEntity mapEntity = (MapEntity) o;

        if (email != null ? !email.equals(mapEntity.email) : mapEntity.email != null) return false;
        return categoryCodes != null ? categoryCodes.equals(mapEntity.categoryCodes) : mapEntity.categoryCodes == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (categoryCodes != null ? categoryCodes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("email", email)
                .add("categoryCodes", categoryCodes)
                .toString();
    }
}
