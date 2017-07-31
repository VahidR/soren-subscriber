package org.vahid.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by vahid (@vahid_r)
 */

public class Category {
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String title;
    private String superCategoryCode;

    public Category() {
    }

    public Category(String code, String title, String superCategoryCode) {
        this.code = code;
        this.title = title;
        this.superCategoryCode = superCategoryCode;
    }

    @JsonProperty
    public String getCode() {
        return code;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getSuperCategoryCode() {
        return superCategoryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (code != null ? !code.equals(category.code) : category.code != null) return false;
        if (title != null ? !title.equals(category.title) : category.title != null) return false;
        return superCategoryCode != null ? superCategoryCode.equals(category.superCategoryCode) : category.superCategoryCode == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (superCategoryCode != null ? superCategoryCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("title", title)
                .add("superCategoryCode", superCategoryCode)
                .toString();
    }
}
