package org.vahid.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.vahid.api.Book;
import org.vahid.api.Category;
import org.vahid.db.SorenSubscriberDAO;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by vahid (@vahid_r)
 */

@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {
    @Mock
    private SorenSubscriberDAO sorenSubscriberDAO;
    @InjectMocks
    private SubscriberService subscriberService = new SubscriberService(sorenSubscriberDAO);
    Category validCategory = new Category("art", "Art", "book");
    Category invalidCategory = new Category("art", null, "book");
    Book book = new Book("Crime and Punishment", Arrays.asList("art"));

    @Test
    public void should_be_able_to_create_category_if_category_is_valid() {
        // Given
        long validCreation = 50;

        // When
        when(sorenSubscriberDAO.createCategory(any(Category.class))).thenReturn(validCreation);
        boolean isCategoryCreationSuccessful = subscriberService.createCategory(validCategory);

        // Then
        assertThat(isCategoryCreationSuccessful, is(true));
    }

    @Test
    public void should_not_create_category_if_category_is_invalid() {
        // Given
        long invalidCreation = -1;

        // When
        when(sorenSubscriberDAO.createCategory(any(Category.class))).thenReturn(invalidCreation);
        boolean isCategoryCreationSuccessful = subscriberService.createCategory(invalidCategory);

        // Then
        assertThat(isCategoryCreationSuccessful, is(false));
    }



    // TODO: Tests for other entities and methods with the same structure..
}