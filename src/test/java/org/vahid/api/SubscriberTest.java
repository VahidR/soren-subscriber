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

public class SubscriberTest {
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void should_serialize_to_json() throws Exception {
        // Given
        String email = "three@three.com";
        List<String> categoryCodes = Arrays.asList("sci", "art", "lit");
        Subscriber subscriber = new Subscriber(email, categoryCodes);

        // When
        String jsonSubscriber = mapper.writeValueAsString(mapper.readValue(fixture("fixtures/subscriber.json"), Subscriber.class));

        // Then
        assertThat(mapper.writeValueAsString(subscriber), is(jsonSubscriber));
    }

    @Test
    public void should_deserialize_from_json() throws Exception {
        // Given / When
        String email = "three@three.com";
        List<String> categoryCodes = Arrays.asList("sci", "art", "lit");
        Subscriber subscriber = new Subscriber(email, categoryCodes);

        // Then
        assertThat(mapper.readValue(fixture("fixtures/subscriber.json"), Subscriber.class).getEmail(), is(subscriber.getEmail()));
        assertThat(mapper.readValue(fixture("fixtures/subscriber.json"), Subscriber.class).getCategoryCodes(), is(subscriber.getCategoryCodes()));
    }
}
