package unit.chronicletoy.service;

import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;
import chronicletoy.service.Time;
import chronicletoy.service.spoof.ActualTime;
import chronicletoy.service.spoof.SleepyVenueService;
import org.junit.Assert;
import org.junit.Test;

public class SleepyVenueServiceTest {

    private MarketPrice price = new MarketPrice();
    private QuoteResponse response = new QuoteResponse();
    private Time time = new ActualTime();
    private SleepyVenueService service = new SleepyVenueService(time);

    @Test
    public void printQuoteResponse() {

        long start = time.nanos();
        System.out.println(start);
        Assert.assertFalse(service.pollResponse(response));

        price.setInstrumentId(5);
        service.placeQuote(price);

        while (!service.pollResponse(response)) {
            time.tickIn(500);
            System.out.println((time.nanos() - start) / 1000000);
        }
        System.out.println(response);
    }
}