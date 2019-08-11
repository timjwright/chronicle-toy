package chronicletoy.engine;

import chronicletoy.model.BidAsk;
import chronicletoy.model.ParameterData;
import chronicletoy.model.StaticData;
import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;
import chronicletoy.model.output.Product;
import chronicletoy.service.OutputService;
import chronicletoy.service.VenueService;

public class ProductLogic {

    private final Product product = new Product();
    private final OutputService ouutputService;
    private final VenueService venue;
    private final MarketPrice quoteRequest = new MarketPrice();

    ProductLogic(int instrumentId, StaticData staticData, OutputService outputService, VenueService venue) {
        this.ouutputService = outputService;
        this.venue = venue;
        product.setInstrumentId(instrumentId);
        staticData.writeTo(product.getStaticData());
    }

    void start() {
        product.setOnMarket(true);
        updateTarget();
    }

    void stop() {
        product.setOnMarket(false);
        product.getLiveQuote().clear();
        writeOutput();
    }

    void setParams(ParameterData params) {
        params.writeTo(product.getParamData());
        updateTarget();
    }

    public void updateUnderlier(MarketPrice underlierPrice) {
        underlierPrice.getBidAsk().writeTo(product.getUnderlier());
        updateTarget();
    }

    public void handleQuoteResponse(QuoteResponse quoteResponse) {
        if ( quoteResponse.getAccepted() && product.isOnMarket() ) {
            product.getPendingQuote().writeTo(product.getLiveQuote());
        }
        product.getPendingQuote().clear();
        submitQuote();
    }

    private void updateTarget() {
        BidAsk underlierQuote = product.getUnderlier();
        double mid = (underlierQuote.getAsk() + underlierQuote.getBid())/2;
        double scaled = mid * product.getStaticData().setScale()/100;
        int spread = Math.max(1,product.getParamData().getSpread());
        int bid = (int)Math.floor(scaled - spread /2.0);
        int offer = (int)Math.ceil(scaled + Math.ceil(spread /2.0));
        product.getTargetQuote().set(1000, bid, offer, 2000);

        submitQuote();
    }

    private void submitQuote() {
        if ( product.getPendingQuote().isClear() && product.isOnMarket() ) {
            product.getTargetQuote().writeTo(product.getPendingQuote());
            quoteRequest.setInstrumentId(product.getInstrumentId());
            product.getTargetQuote().writeTo(quoteRequest.getBidAsk());
            venue.placeQuote(quoteRequest);
        }
        writeOutput();
    }

    private void writeOutput() {
        ouutputService.write(product);
    }
}
