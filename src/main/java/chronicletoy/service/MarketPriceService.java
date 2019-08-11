package chronicletoy.service;

import chronicletoy.model.input.MarketPrice;

public interface MarketPriceService {
    boolean pollQuote(MarketPrice buffer);
}
