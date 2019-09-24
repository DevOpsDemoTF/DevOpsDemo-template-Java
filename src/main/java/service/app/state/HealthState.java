package service.app.state;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthState implements HealthIndicator {
    private boolean healthy = true;

    public void setHealthy() {
        this.healthy = true;
    }

    public void setUnhealthy() {
        this.healthy = false;
    }

    @Override
    public Health health() {
        if (healthy) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}
