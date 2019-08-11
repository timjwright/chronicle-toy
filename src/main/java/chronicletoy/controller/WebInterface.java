package chronicletoy.controller;

import spark.Service;

import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

public class WebInterface {

    private final Controller controller;
    private final View view;

    public WebInterface(Controller controller, View view) {
        this.controller = controller;
        this.view = view;
        startWebInterface();
    }

    private void startWebInterface() {
        Service service = Service.ignite();
        service.port(8080);
        service.get("/start/:product", (req, res) -> {
            int product = parseInt(req.params("product"));
            controller.start(product);
            return view.get(product);});
        service.get("/stop/:product", (req, res) -> {
            int product = parseInt(req.params("product"));
            controller.stop(product);
            return view.get(product);});
        service.get("/spread/:product/:spread", (req, res) -> {
            int product = parseInt(req.params("product"));
            controller.setParameters(product, parseInt(req.params("spread")));
            return view.get(product);});
        service.get("/view/:product", (req, res) -> view.get(parseInt(req.params("product"))));
        service.get("/view", (req, res) -> String.join("\n", view.get()));
        service.init();
    }
}
