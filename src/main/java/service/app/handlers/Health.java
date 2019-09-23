package service.app.handlers;

import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Health {
    private final HealthEndpoint healthEndpoint;

    public Health(HealthEndpoint healthEndpoint) {
        this.healthEndpoint = healthEndpoint;
    }

    @GetMapping("/health")
    public void health() {
        var health = this.healthEndpoint.health();
        var status = health.getStatus();

        if (status != Status.UP) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, status.toString());
        }
    }
}