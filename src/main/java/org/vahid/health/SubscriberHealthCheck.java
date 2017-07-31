package org.vahid.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import org.vahid.services.SubscriberService;

import static org.vahid.util.Constants.HEALTHY_MESSAGE;
import static org.vahid.util.Constants.UNHEALTHY_MESSAGE;

/**
 * Created by vahid (@vahid_r)
 */

public class SubscriberHealthCheck extends HealthCheck {
    private SubscriberService subscriberService;

    @Inject
    public SubscriberHealthCheck(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @Override
    protected Result check() throws Exception {
        boolean databaseHealthStatus = subscriberService.isSystemHealthy();
        if (databaseHealthStatus) {
            return Result.healthy(HEALTHY_MESSAGE.value);
        } else {
            return Result.unhealthy(UNHEALTHY_MESSAGE.value);
        }
    }
}
