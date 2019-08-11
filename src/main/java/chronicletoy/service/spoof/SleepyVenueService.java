package chronicletoy.service.spoof;

import chronicletoy.Universe;
import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;
import chronicletoy.service.Time;
import chronicletoy.service.VenueService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SleepyVenueService implements VenueService {
    private static final int MAX_TIME = (int)TimeUnit.SECONDS.toMillis(3);
    private final Time time;
    private final long[] times = new long[Universe.PRODUCTS];
    private Random random = new Random();

    public SleepyVenueService(Time time) {
        this.time = time;
    }

    @Override
    public boolean pollResponse(QuoteResponse buffer) {
        long now = time.nanos();
        for ( int i = 0 ; i < times.length ; i++ ) {
            if ( times[i] < now && times[i] != 0 ) {
                buffer.setInstrumentId(i);
                buffer.setAccepted(random.nextGaussian() > -0.8);
                times[i] = 0;
                return true;
            }
        }
        return false;
    }

    @Override
    public void placeQuote(MarketPrice quote) {
        times[quote.getInstrumentId()] = time.nanos() + TimeUnit.MILLISECONDS.toNanos(random.nextInt(MAX_TIME));
    }
}
