package chronicletoy.service.spoof;

import chronicletoy.Universe;
import chronicletoy.controller.View;
import chronicletoy.model.output.Product;
import chronicletoy.service.OutputService;

public class ArrayOutputServiceView implements View, OutputService {
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
        System.out.println("Updating: " + product);
        state[product.getInstrumentId()] = product.toString();
    }
}
