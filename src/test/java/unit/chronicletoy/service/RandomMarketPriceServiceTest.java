package unit.chronicletoy.service;

import chronicletoy.model.input.MarketPrice;
import chronicletoy.service.spoof.ActualTime;
import chronicletoy.service.spoof.RandomMarketPriceService;
import chronicletoy.service.Time;
import org.junit.Test;

public class RandomMarketPriceServiceTest {

    private MarketPrice price = new MarketPrice();
    private Time time = new ActualTime();
    private RandomMarketPriceService service = new RandomMarketPriceService(time);

    @Test
    public void printMarketPriceTick() {
        System.out.println("Tick");
        time.tick();
        while ( service.pollQuote( price ) ) {
            System.out.println(price);
        }
    }


    @Test
    public void printMarketPriceManyTicks() {
        for ( int i = 0 ; i < 100 ; i++ ) {
            printMarketPriceTick();
        }
    }
}
