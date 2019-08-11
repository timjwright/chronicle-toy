package chronicletoy.service.spoof;

import chronicletoy.service.Time;

public class ActualTime implements Time {
    private long now = System.nanoTime();

    @Override
    public void tick() {
        now = System.nanoTime();
    }

    @Override
    public void tickIn(long ms) {
        try {
            Thread.sleep(ms);
            tick();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public long nanos() {
        return now;
    }
}
