package org.vahid.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */

public class BookTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void should_serialize_to_json() throws Exception {
        // Given
        String title = "Yanni collection";
        List<String> categoryCodes = Arrays.asList("mus");
        Book book = new Book(title, categoryCodes);

        // When
        String jsonBook = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/book.json"), Book.class));

        // Then
        assertThat(mapper.writeValueAsString(book), is(jsonBook));
    }

    @Test
    public void should_deserialize_from_json() throws Exception {
        // Given / When
        String title = "Yanni collection";
        List<String> categoryCodes = Arrays.asList("mus");
        Book book = new Book(title, categoryCodes);

        // Then
        assertThat(mapper.readValue(fixture("fixtures/book.json"), Book.class).getTitle(), is(book.getTitle()));
        assertThat(mapper.readValue(fixture("fixtures/book.json"), Book.class).getCategoryCodes(), is(book.getCategoryCodes()));
    }
}
