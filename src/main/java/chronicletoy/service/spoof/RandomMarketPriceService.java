package chronicletoy.service.spoof;

import chronicletoy.Universe;
import chronicletoy.model.input.MarketPrice;
import chronicletoy.service.MarketPriceService;
import chronicletoy.service.Time;

import java.util.Random;

public class RandomMarketPriceService implements MarketPriceService {
    private final Time time;
    private int count = 0;
    private long lastTime = 0;
    private final Random random = new Random();
    private final int prices[] = new int[Universe.UL_PRODUCTS];

    public RandomMarketPriceService(Time time) {
        this.time = time;
        for ( int i = 0 ; i < prices.length ; i++) {
            prices[i] = (i+1)*1000;
        }
    }


    @Override
    public boolean pollQuote(MarketPrice buffer) {
        if ( shouldPollQuote() ) {
            fillQuote(buffer);
            return true;
        } else {
            return false;
        }

    }

    private boolean shouldPollQuote() {
        if ( lastTime != time.nanos() ) {
            count = Math.max(1,random.nextInt(Universe.UL_PRODUCTS));
            lastTime = time.nanos();
            return true;
        } else {
            return count-- != 0;
        }
    }

    private void fillQuote(MarketPrice buffer) {
        int instrumentId = random.nextInt(Universe.UL_PRODUCTS);

        int price = prices[instrumentId] + move();
        prices[instrumentId] = price;
        buffer.setInstrumentId(instrumentId);
        buffer.getBidAsk().set(1000, price, price + instrumentId + 1, 1500);
    }

    private int move() {
        double g = random.nextGaussian();
        if ( g < -0.5 ) {
            return -1;
        } else if ( g > 0.5 ) {
            return 1;
        } else {
            return 0;
        }
    }
}
