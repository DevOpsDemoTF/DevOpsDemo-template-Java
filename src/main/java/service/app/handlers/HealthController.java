package service.app.handlers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class HealthController {
    private final HealthEndpoint healthEndpoint;
    private final Counter counter;

    public HealthController(HealthEndpoint healthEndpoint, MeterRegistry metrics) {
        this.healthEndpoint = healthEndpoint;
        this.counter = metrics.counter("health_counter");
    }

    @GetMapping("/health")
    public void get_health() {
        this.counter.increment();

        var health = this.healthEndpoint.health();
        var status = health.getStatus();

        if (status != Status.UP) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, status.toString());
        }
    }
}
