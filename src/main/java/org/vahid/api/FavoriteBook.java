package org.vahid.api;

import com.google.common.base.MoreObjects;

/**
 * Created by vahid (@vahid_r)
 */

public class FavoriteBook {
    private String categoryPath;
    private String book;

    public FavoriteBook() {
    }

    public FavoriteBook(String categoryPath, String book) {
        this.categoryPath = categoryPath;
        this.book = book;
    }

    public String getCategoryPpath() {
        return categoryPath;
    }

    public String getBook() {
        return book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteBook that = (FavoriteBook) o;

        if (categoryPath != null ? !categoryPath.equals(that.categoryPath) : that.categoryPath != null) return false;
        return book != null ? book.equals(that.book) : that.book == null;
    }

    @Override
    public int hashCode() {
        int result = categoryPath != null ? categoryPath.hashCode() : 0;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("categoryPath", categoryPath)
                .add("book", book)
                .toString();
    }
}
