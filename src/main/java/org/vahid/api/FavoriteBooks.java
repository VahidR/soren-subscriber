package org.vahid.api;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */

public class FavoriteBooks {
    private String email;
    private List<List<FavoriteBook>> favoriteBooks;

    public FavoriteBooks() {
    }

    public FavoriteBooks(String email, List<List<FavoriteBook>> favoriteBooks) {
        this.email = email;
        this.favoriteBooks = favoriteBooks;
    }

    public String getEmail() {
        return email;
    }

    public List<List<FavoriteBook>> getFavoriteBooks() {
        return favoriteBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteBooks that = (FavoriteBooks) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return favoriteBooks != null ? favoriteBooks.equals(that.favoriteBooks) : that.favoriteBooks == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (favoriteBooks != null ? favoriteBooks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("email", email)
                .add("favoriteBooks", favoriteBooks)
                .toString();
    }
}
