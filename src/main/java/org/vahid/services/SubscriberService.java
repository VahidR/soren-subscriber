package org.vahid.services;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vahid.api.*;
import org.vahid.db.SorenSubscriberDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by vahid (@vahid_r)
 */

public class SubscriberService {
    private static final Logger log = LoggerFactory.getLogger(SubscriberService.class);

    SorenSubscriberDAO sorenSubscriberDAO;

    @Inject
    public SubscriberService(SorenSubscriberDAO sorenSubscriberDAO) {
        this.sorenSubscriberDAO = sorenSubscriberDAO;
    }

    /**
     * Method to create a category.
     * @param category object
     * @return boolean status of being successful to create a category or not
     */
    public boolean createCategory(Category category) {
        log.debug("Category in Service: {}", category.toString());
        boolean success = true;
        long lastId = sorenSubscriberDAO.createCategory(category);
        if (lastId < 0) {
            success = false;
        }

        return success;
    }

    /**
     * Method to create a book.
     * @param book object
     * @return boolean status of being successful to create a book or not
     */
    public boolean createBook(Book book) {
        log.debug("Category in Service: {}", book.toString());
        boolean success = true;
        List<String> categoryCodes = book.getCategoryCodes();
        long lastId = sorenSubscriberDAO.createBook(book.getTitle(),
                book.getCategoryCodes().toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        if (lastId < 0) {
            success = false;
        } else {
            categoryCodes.stream()
                    .map(categoryCode -> sorenSubscriberDAO.getCategoryPath(categoryCode))
                    .forEach(path -> sorenSubscriberDAO.insertIntoCategoryBookTable(path, book.getTitle()));
        }

        return success;
    }

    /**
     * Method to create a subscriber.
     * @param subscriber object
     * @return boolean status of being successful to create a book or not
     */
    public boolean createSubscriber(Subscriber subscriber) {
        log.debug("Category in Service: {}", subscriber.toString());
        boolean success = true;
        List<String> categoryCodes = subscriber.getCategoryCodes()
                .stream()
                .map(cat -> sorenSubscriberDAO.getCategoryName(cat))
                .collect(Collectors.toList());
        long lastId = sorenSubscriberDAO.createSubscriber(subscriber.getEmail(),
                categoryCodes.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        if (lastId < 0) {
            success = false;
        }

        return success;
    }

    /**
     * Method to return list of MapEntity objects, containing the email and categoryCodes for all subscribers
     * @return list of MapEntity objects
     */
    public List<MapEntity> getSubscribersWithCats() {
        List<MapEntity> subscribersWithCats = sorenSubscriberDAO.getSubscribersWithCats();
        return subscribersWithCats;
    }

    /**
     * It discovers the the list of books and their relevant paths for each subscriber that is in the system.
     * @param email email of the subscriber
     * @param categoryCodes the favorite category codes that he/she has subscribed into
     * @return {@code List<List<FavoriteBook>>} list of books and their relevant paths
     */
    public FavoriteBooks getListOfBooksOfTheirInterests(String email, String categoryCodes) {
        List<List<FavoriteBook>> pathAndBook = new ArrayList<>();
        for (String cat : categoryCodes.split(",")) {
           pathAndBook.add(sorenSubscriberDAO.getPathAndBookForThisUser(cat.trim()));
        }
        FavoriteBooks favoriteBooks = new FavoriteBooks(email, pathAndBook);

        return favoriteBooks;
    }

    /**
     * It checks the health check of the system.
     * It is essentially does a query over the subscriber table to see if database is responsive or not.
     * @return boolean status of the database responsiveness
     */
    public boolean isSystemHealthy() {
        boolean isHealthy = true;
        try {
            sorenSubscriberDAO.getSubscribersWithCats();
        } catch (Exception ex) {
            isHealthy = false;
            log.error(ex.getMessage());
        }
        return isHealthy;
    }

}
