package chronicletoy;

import chronicletoy.controller.WebInterface;
import chronicletoy.engine.Engine;
import chronicletoy.service.*;
import chronicletoy.service.spoof.*;

public class SimpleOneProcessMain {
    public static void main(String[] args) {

        Time time = new ActualTime();

        MarketPriceService marketPriceService = new RandomMarketPriceService(time);
        LinkedListBackedCommandService commandService = new LinkedListBackedCommandService();
        VenueService venueSerice = new SleepyVenueService(time);
        ArrayOutputServiceView outputServiceAndView = new ArrayOutputServiceView();

        Engine engine = new Engine(time, marketPriceService, commandService, venueSerice, outputServiceAndView);
        Thread thread = new Thread(engine);
        thread.start();

        new WebInterface(commandService, outputServiceAndView);
    }
}
