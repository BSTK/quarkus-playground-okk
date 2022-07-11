package dev.bstk.gatwayapi.health;

import dev.bstk.gatwayapi.resource.HealtCheckResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.inject.Inject;

@Liveness
public class ApisHealthCheck implements HealthCheck {

    @Inject
    protected HealtCheckResource resource;

    @Override
    public HealthCheckResponse call() {
        resource.ping();
        return HealthCheckResponse
            .named("Api's Resources Health Check")
            .up()
            .build();
    }
}
