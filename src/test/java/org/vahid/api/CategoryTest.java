package org.vahid.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vahid
 * Date 2017-07-31
 */

public class CategoryTest {
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void should_serialize_to_json() throws Exception {
        // Given
        String code = "mech";
        String title = "Mechanics";
        String superCategoryCode = "phy";
        final Category category = new Category(code, title, superCategoryCode);

        // When
        String jsonCategory = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/category.json"), Category.class));

        // Then
        assertThat(mapper.writeValueAsString(category), is(jsonCategory));
    }

    @Test
    public void should_deserialize_from_json() throws Exception {
        // Given / When
        String code = "mech";
        String title = "Mechanics";
        String superCategoryCode = "phy";
        Category category = new Category(code, title, superCategoryCode);

        // Then
        assertThat(mapper.readValue(fixture("fixtures/category.json"), Category.class).getCode(), is(category.getCode()));
        assertThat(mapper.readValue(fixture("fixtures/category.json"), Category.class).getTitle(), is(category.getTitle()));
        assertThat(mapper.readValue(fixture("fixtures/category.json"), Category.class).getSuperCategoryCode(), is(category.getSuperCategoryCode()));
    }
}
