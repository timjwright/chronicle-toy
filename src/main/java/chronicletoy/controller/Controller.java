package chronicletoy.controller;

public interface Controller {
    void start(int instrumentId);

    void stop(int instrumentId);

    void setParameters(int instrumentId, int spread);

    void staticData(int instrumentId, int underlier, int scale);
}
