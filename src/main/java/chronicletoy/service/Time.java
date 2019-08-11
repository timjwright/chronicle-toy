package chronicletoy.service;

public interface Time {
    void tick();
    void tickIn(long ms);
    long nanos();
}
