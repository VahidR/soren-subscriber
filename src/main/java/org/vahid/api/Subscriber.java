package org.vahid.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */

public class Subscriber {
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private List<String> categoryCodes;

    public Subscriber() {
    }

    public Subscriber(String email, List<String> categoryCodes) {
        this.email = email;
        this.categoryCodes = categoryCodes;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public List<String> getCategoryCodes() {
        return categoryCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriber that = (Subscriber) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return categoryCodes != null ? categoryCodes.equals(that.categoryCodes) : that.categoryCodes == null;
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
