package org.vahid.api;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */

public class NewsLetter {
    private String recipient;
    private List<List<FavoriteBook>> notifications;

    public NewsLetter() {
    }

    public NewsLetter(String recipient, List<List<FavoriteBook>> notifications) {
        this.recipient = recipient;
        this.notifications = notifications;
    }

    public String getRecipient() {
        return recipient;
    }

    public List<List<FavoriteBook>> getNotifications() {
        return notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsLetter that = (NewsLetter) o;

        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;
        return notifications != null ? notifications.equals(that.notifications) : that.notifications == null;
    }

    @Override
    public int hashCode() {
        int result = recipient != null ? recipient.hashCode() : 0;
        result = 31 * result + (notifications != null ? notifications.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("recipient", recipient)
                .add("notifications", notifications)
                .toString();
    }
}
