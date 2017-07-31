package org.vahid.resources;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vahid.api.*;
import org.vahid.services.SubscriberService;
import static org.vahid.util.Constants.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */



@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubscriberResource {
    private static final Logger log = LoggerFactory.getLogger(SubscriberResource.class);

    @Inject
    SubscriberService subscriberService;

    @GET
    @Path("/ping")
    public Response ping() {
        log.debug("PING called !!");
        return Response.ok().entity("PONG!!").build();
    }

    @POST
    @Path("/categories")
    public Response createCategory(@Valid Category category) {
        log.debug("category in Resource: {}", category.toString());
        boolean isSuccessful = subscriberService.createCategory(category);
        if (isSuccessful) {
            ResponseStatus responseStatus = new ResponseStatus(CATEGORY_ENTITY.value, category.getTitle(), true);
            return Response.ok(responseStatus).build();
        }
        return Response.serverError().entity(RESOURCE_NOT_OK.value).build();
    }

    @POST
    @Path("/books")
    public Response createBook(@Valid Book book) {
        boolean isSuccessful = subscriberService.createBook(book);
        if (isSuccessful) {
            ResponseStatus responseStatus = new ResponseStatus(BOOK_ENTITY.value, book.getTitle(), true);
            return Response.ok(responseStatus).build();
        }
        return Response.serverError().entity(RESOURCE_NOT_OK.value).build();
    }

    @POST
    @Path("/subscribers")
    public Response createSubscriber(@Valid Subscriber subscriber) {
        boolean isSuccessful = subscriberService.createSubscriber(subscriber);
        if (isSuccessful) {
            ResponseStatus responseStatus = new ResponseStatus(SUBSCRIBER_ENTITY.value, subscriber.getEmail(), true);
            return Response.ok(responseStatus).build();
        }
        return Response.serverError().entity(RESOURCE_NOT_OK.value).build();
    }

    @GET
    @Path("/newsletters")
    public Response getNewsLetters() {
        List<NewsLetter> newsLetters = new ArrayList<>();
        List<MapEntity> subscribersWithCats = subscriberService.getSubscribersWithCats();
        FavoriteBooks userFavorites = null;
        NewsLetter newsLetter = null;
        for (MapEntity user : subscribersWithCats) {
            userFavorites = subscriberService.getListOfBooksOfTheirInterests(user.getEmail(), user.getCategoryCodes());
            newsLetter = new NewsLetter(user.getEmail(), userFavorites.getFavoriteBooks());
            newsLetters.add(newsLetter);
        }

        return Response.ok(newsLetters).build();
    }


}
