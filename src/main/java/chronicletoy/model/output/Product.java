package chronicletoy.model.output;

import chronicletoy.model.BidAsk;
import chronicletoy.model.ParameterData;
import chronicletoy.model.StaticData;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class Product {
    public final static int SIZE = 128;

    private final MutableDirectBuffer buffer;
    private final StaticData staticData = new StaticData();
    private final ParameterData paramData = new ParameterData();
    private final BidAsk underlier = new BidAsk();
    private final BidAsk market = new BidAsk();
    private final BidAsk quote = new BidAsk();
    private final BidAsk target = new BidAsk();

    public Product() {
        this.buffer = new UnsafeBuffer(ByteBuffer.allocateDirect(SIZE));
        int offset = staticData.wrap(buffer, 10);
        offset = paramData.wrap(buffer, offset);
        offset = underlier.wrap(buffer, offset);
        offset = market.wrap(buffer, offset);
        offset = quote.wrap(buffer, offset);
        offset = target.wrap(buffer, offset);
    }

    public MutableDirectBuffer getBuffer() {
        return buffer;
    }

    public int getInstrumentId() {
        return buffer.getInt(0);
    }

    public void setInstrumentId(int instrumentId) {
        buffer.putInt(0, instrumentId);
    }

    public boolean isOnMarket() {
        return buffer.getByte(4) != 0;
    }
    public void setOnMarket(boolean onMarket) {
        buffer.putByte(4, (byte)(onMarket ? 1 : 0));
    }

    public StaticData getStaticData() {
        return staticData;
    }

    public ParameterData getParamData() {
        return paramData;
    }

    public BidAsk getUnderlier() {
        return underlier;
    }
    public BidAsk getLiveQuote() {
        return market;
    }

    public BidAsk getTargetQuote() {
        return quote;
    }

    public BidAsk getPendingQuote() {
        return target;
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append("[instrument: ").
                append(getInstrumentId()).
                append(", onMarket: ").
                append(isOnMarket()).
                append(", static: [").
                append(getStaticData()).
                append("], params: [").
                append(getParamData()).
                append("], underlier: [").
                append(getUnderlier()).
                append("], target: [").
                append(getTargetQuote()).
                append("], live: [").
                append(getLiveQuote()).
                append("], pending: [").
                append(getPendingQuote()).
                append("]").
                toString();
    }
}
