package chronicletoy.model;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

public class BidAsk {
    public final static int SIZE = Integer.BYTES*4;

    private final MutableDirectBuffer buffer = new UnsafeBuffer();

    public int wrap(MutableDirectBuffer buffer, int offset) {
        this.buffer.wrap(buffer, offset, SIZE);
        return offset + SIZE;
    }

    public void writeTo(BidAsk other) {
        other.buffer.putBytes(0, this.buffer, 0, SIZE);
    }

    public void clear() {
        setBid(0);
        setAsk(0);
        setAskSize(0);
        setBidSize(0);
    }

    public boolean isClear() {
        return getAskSize() == 0 && getBidSize() == 0;
    }
    public void set(int bidSize, int bid, int ask, int askSize) {
        setBidSize(bidSize);
        setAskSize(askSize);
        setBid(bid);
        setAsk(ask);
    }

    public int getBid() {
        return buffer.getInt(0);
    }

    public void setBid(int bid) {
        buffer.putInt(0, bid);
    }

    public int getAsk() {
        return buffer.getInt(4);
    }

    public void setAsk(int ask) {
        buffer.putInt(4, ask);
    }

    public int getBidSize() {
        return buffer.getInt(8);
    }

    public void setBidSize(int bidSize) {
        buffer.putInt(8, bidSize);
    }

    public int getAskSize() {
        return buffer.getInt(12);
    }

    public void setAskSize(int askSize) {
        buffer.putInt(12, askSize);
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append(getBidSize()).
                append(" ").
                append(getBid()).
                append(" x ").
                append(getAsk()).
                append(" ").
                append(getAskSize()).
                toString();
    }

}
