package chronicletoy.service.spoof;

import chronicletoy.Universe;
import chronicletoy.controller.View;
import chronicletoy.model.output.Product;
import chronicletoy.service.OutputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayOutputServiceView implements View, OutputService {
    Logger logger = LoggerFactory.getLogger(ArrayOutputServiceView.class);
    private String[] state = new String[Universe.PRODUCTS];

    @Override
    public String[] get() {
        return state;
    }

    @Override
    public String get(int instrumentId) {
        return state[instrumentId];
    }

    @Override
    public void write(Product product) {
        logger.debug("Output " + product);
        state[product.getInstrumentId()] = product.toString();
    }
}
