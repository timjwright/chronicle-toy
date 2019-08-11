package chronicletoy.engine;

import chronicletoy.Universe;
import chronicletoy.model.input.Command;
import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;
import chronicletoy.service.OutputService;
import chronicletoy.service.Time;
import chronicletoy.service.VenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Logic {
    private static Logger logger = LoggerFactory.getLogger(Logic.class);
    private final Time time;
    private final VenueService venueService;
    private final OutputService outputService;
    private final ProductLogic[] instances = new ProductLogic[Universe.PRODUCTS];
    private final ArrayList<Integer>[] ulToProduct= new ArrayList[Universe.UL_PRODUCTS];

    public Logic(Time time, VenueService venueService, OutputService outputService) {
        this.time = time;
        this.venueService = venueService;
        this.outputService = outputService;
        for ( int i = 0 ; i < Universe.UL_PRODUCTS ; i++ ) {
            ulToProduct[i] = new ArrayList<>(Universe.PRODUCTS);
        }
    }

    public void updateUnderlier(MarketPrice priceUpdate) {
        try {
            ArrayList<Integer> products = ulToProduct[priceUpdate.getInstrumentId()];
            for ( int i = 0 ; i < products.size() ; i++ ) {
                instances[products.get(i)].updateUnderlier(priceUpdate);
            }
        } catch (Exception e) {
            logger.error("Exception handling price {}", priceUpdate, e);
        }
    }

    public void handleQuoteResponse(QuoteResponse quoteResponse) {
        try {
            System.out.println("Quote response " + quoteResponse);
            instances[quoteResponse.getInstrumentId()].handleQuoteResponse(quoteResponse);
        } catch (Exception e) {
            logger.error("Exception handling quote response {}", quoteResponse, e);
        }
    }

    public void handleCommand(Command command) {
        try {
            ProductLogic instance = instances[command.getInstrumentId()];
            switch (command.getCommand()) {
                case STATIC:
                    instance = new ProductLogic(command.getInstrumentId(), command.getStaticData(), outputService, venueService);
                    instances[command.getInstrumentId()] = instance;
                    ulToProduct[command.getStaticData().getUnderlying()].add(command.getInstrumentId());
                    break;

                case START:
                    instance.start();
                    break;
                case STOP:
                    instance.stop();
                    break;
                case PARAMETERS:
                    instance.setParams(command.getParameterData());
            }
        } catch (Exception e) {
            logger.error("Exception handling command: {}", command, e);
        }
    }

}
