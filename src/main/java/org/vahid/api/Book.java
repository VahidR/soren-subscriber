package org.vahid.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */

public class Book {
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private List<String> categoryCodes;

    public Book() {
    }

    public Book(String title, List<String> categoryCodes) {
        this.title = title;
        this.categoryCodes = categoryCodes;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public List<String> getCategoryCodes() {
        return categoryCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return categoryCodes != null ? categoryCodes.equals(book.categoryCodes) : book.categoryCodes == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (categoryCodes != null ? categoryCodes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("categoryCodes", categoryCodes)
                .toString();
    }
}
