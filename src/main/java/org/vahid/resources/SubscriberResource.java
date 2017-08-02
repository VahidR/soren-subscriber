package org.vahid.resources;

import static org.vahid.util.Constants.BOOK_ENTITY;
import static org.vahid.util.Constants.CATEGORY_ENTITY;
import static org.vahid.util.Constants.RESOURCE_NOT_OK;
import static org.vahid.util.Constants.SUBSCRIBER_ENTITY;

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vahid.api.Book;
import org.vahid.api.Category;
import org.vahid.api.FavoriteBooks;
import org.vahid.api.MapEntity;
import org.vahid.api.NewsLetter;
import org.vahid.api.ResponseStatus;
import org.vahid.api.Subscriber;
import org.vahid.services.SubscriberService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by vahid (@vahid_r)
 */



@Path("/api/v1")
@Api(value = "/api/v1", description = "Subscriber endpoints")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubscriberResource {
    private static final Logger log = LoggerFactory.getLogger(SubscriberResource.class);

    @Inject
    SubscriberService subscriberService;

    @GET
    @Path("/ping")
    @ApiOperation(
        value = "Request a pong.",
        response = Response.class)
    public Response ping() {
        log.debug("PING called !!");
        return Response.ok().entity("PONG!!").build();
    }

    @POST
    @Path("/categories")
    @ApiOperation(
        value = "Create a new category",
        response = Response.class)
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
    @ApiOperation(
        value = "Create a new book",
        response = Response.class)
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
    @ApiOperation(
        value = "Create a new subscriber",
        response = Response.class)
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
    @ApiOperation(
        value = "Get a newsletter",
        response = Response.class)
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
