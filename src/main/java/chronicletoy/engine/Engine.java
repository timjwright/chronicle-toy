package chronicletoy.engine;

import chronicletoy.model.input.Command;
import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;
import chronicletoy.service.*;

public class Engine implements  Runnable {

    private volatile boolean stop = false;
    private final Time time;
    private final MarketPriceService marketPriceService;
    private final CommandService commandService;
    private final VenueService venueService;
    private final Logic logic;

    public Engine(
            Time time,
            MarketPriceService marketPriceService,
            CommandService commandService,
            VenueService venueService,
            OutputService outputService) {
        this.time = time;
        this.marketPriceService = marketPriceService;
        this.commandService = commandService;
        this.venueService = venueService;
        this.logic = new Logic(time, venueService, outputService);
    }

    @Override
    public void run() {
        MarketPrice price = new MarketPrice();
        Command command = new Command();
        QuoteResponse quoteResponse = new QuoteResponse();

        commandService.bootstrap();
        while ( ! stop && commandService.pollCommand(command)) {
            logic.handleCommand(command);
        }

        while ( ! stop ) {
            time.tickIn(10);
            while ( ! stop && marketPriceService.pollQuote(price)) {
                logic.updateUnderlier(price);
            }
            while ( ! stop && venueService.pollResponse(quoteResponse)) {
                logic.handleQuoteResponse(quoteResponse);
            }
            if ( ! stop && commandService.pollCommand(command)) {
                logic.handleCommand(command);
            }
        }
    }

    public void stop() {
        stop = true;
    }
}
