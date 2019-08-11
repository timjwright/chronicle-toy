package unit.chronicletoy.model;

import chronicletoy.model.input.Command;
import chronicletoy.model.input.CommandType;
import chronicletoy.model.input.MarketPrice;
import chronicletoy.model.input.QuoteResponse;
import chronicletoy.model.output.Product;
import org.junit.Test;

public class ModelPrintTest {
    private MarketPrice price = new MarketPrice();
    private QuoteResponse quoteResponse = new QuoteResponse();
    private Product product = new Product();
    private Command command = new Command();

    @Test
    public void printMarketPrice() {
        price.setInstrumentId(10);
        price.getBidAsk().set(1000,10,12,2000);
        System.out.println(price);
    }

    @Test
    public void printQuoteResponse() {
        quoteResponse.setInstrumentId(10);
        quoteResponse.setAccepted(true);
        System.out.println(quoteResponse);
    }

    @Test
    public void printProduct() {
        product.setInstrumentId(10);
        product.getStaticData().setScale(20);
        product.getStaticData().getUnderlying(5);
        product.getParamData().setSpread(20);
        product.getLiveQuote().set(1000,10,12,2000);
        product.getTargetQuote().set(1001,11,13,2001);
        product.getPendingQuote().set(1002,12,14,2002);
        System.out.println(product);
    }

    @Test
    public void printStartCommand() {
        command.setCommand(CommandType.START, 10);
        System.out.println(command);
        Command otherCommand = new Command();
        command.writeTo(otherCommand);
        System.out.println(otherCommand);
    }


    @Test
    public void printStopCommand() {
        command.setCommand(CommandType.STOP, 11);
        System.out.println(command);
    }

    @Test
    public void printStaticCommand() {
        command.setCommand(CommandType.STATIC,12);
        command.getStaticData().setScale(20);
        command.getStaticData().getUnderlying(5);
        System.out.println(command);
    }

    @Test
    public void printParamCommand() {
        command.setCommand(CommandType.PARAMETERS, 13);
        command.getParameterData().setSpread(20);
        System.out.println(command);
    }
}
