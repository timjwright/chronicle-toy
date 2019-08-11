package chronicletoy.service;

import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;

public interface VenueService {
    boolean pollResponse(QuoteResponse buffer);
    void placeQuote(MarketPrice quote);
}