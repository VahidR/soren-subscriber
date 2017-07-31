package org.vahid;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.vahid.db.SorenSubscriberDAO;
import org.vahid.health.SubscriberHealthCheck;
import org.vahid.resources.SubscriberResource;

import static org.vahid.util.Constants.DATABASE_TYPE;
import static org.vahid.util.Constants.APPLICATION_NAME;


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

        // Health Check registration
        environment.jersey().register(injector.getInstance(SubscriberHealthCheck.class));

        // Resources registration
        environment.jersey().register(injector.getInstance(SubscriberResource.class));
    }

}
