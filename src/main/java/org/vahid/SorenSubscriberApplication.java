package org.vahid;

import static org.vahid.util.Constants.APPLICATION_NAME;
import static org.vahid.util.Constants.DATABASE_TYPE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import org.skife.jdbi.v2.DBI;
import org.vahid.db.SorenSubscriberDAO;
import org.vahid.health.SubscriberHealthCheck;
import org.vahid.resources.SubscriberResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;


public class SorenSubscriberApplication extends Application<SorenSubscriberConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SorenSubscriberApplication().run(args);
    }

    @Override
    public String getName() {
        return APPLICATION_NAME.value;
    }

    @Override
    public void initialize(final Bootstrap<SorenSubscriberConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new AssetsBundle("/swagger-ui", "/docs", "index.html"));
    }

    @Override
    public void run(final SorenSubscriberConfiguration configuration, final Environment environment) {
        // Registering DAOs and creating Guice
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                DBIFactory factory = new DBIFactory();
                DBI dbi = factory.build(environment, configuration.getDataSourceFactory(), DATABASE_TYPE.value);
                SorenSubscriberDAO sorenSubscriberDAO = dbi.onDemand(SorenSubscriberDAO.class);
                bind(SorenSubscriberDAO.class).toInstance(sorenSubscriberDAO);
            }
        });

        // Swagger doc endpoint
        environment.jersey().register(new ApiListingResource());

        // Health Check registration
        environment.jersey().register(injector.getInstance(SubscriberHealthCheck.class));

        // Resources registration
        environment.jersey().register(injector.getInstance(SubscriberResource.class));

        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        BeanConfig config = new BeanConfig();
        config.setTitle("Swagger sample app");
        config.setVersion("1.0.0");
        config.setResourcePackage("org.vahid.resources");
        config.setScan(true);
    }

}
