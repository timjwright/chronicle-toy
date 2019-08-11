package chronicletoy.model.input;

import chronicletoy.model.BidAsk;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class MarketPrice {
    public final static int SIZE = BidAsk.SIZE + 4;

    private final MutableDirectBuffer buffer;
    private final BidAsk bidAsk = new BidAsk();

    public MarketPrice() {
        this.buffer = new UnsafeBuffer(ByteBuffer.allocateDirect(SIZE));
        int offset = bidAsk.wrap(buffer, 4);
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

    public BidAsk getBidAsk() {
        return bidAsk;
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append("[instrument: ").
                append(getInstrumentId()).
                append(", bidask: ").
                append(getBidAsk()).
                append("]").
                toString();
    }
}
