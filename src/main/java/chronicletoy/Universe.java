package chronicletoy;

import chronicletoy.controller.Controller;

public class Universe {

    public static int PRODUCTS = 20;
    public static int UL_PRODUCTS = 5;

    public static void bootstrap(Controller controller) {
        controller.staticData(0, 0, 100);
        controller.staticData(1, 0, 110);
        controller.staticData(2, 0, 90);
        controller.staticData(3, 0, 120);
        controller.staticData(4, 0, 80);
        controller.staticData(5, 0, 130);
        controller.staticData(6, 0, 700);
        controller.staticData(7, 0, 140);
        controller.staticData(8, 1, 120);
        controller.staticData(9, 1, 140);
        controller.staticData(10, 1, 80);
        controller.staticData(11, 1, 60);
        controller.staticData(12, 1, 100);
        controller.staticData(13, 2, 110);
        controller.staticData(14, 2, 120);
        controller.staticData(15, 2, 130);
        controller.staticData(16, 2, 140);
        controller.staticData(17, 3, 100);
        controller.staticData(18, 3, 110);
        controller.staticData(19, 4, 100);
    }
}
