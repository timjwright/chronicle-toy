package chronicletoy.model.input;

import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class QuoteResponse {
    public final static int SIZE = 8;
    private final UnsafeBuffer buffer;

    public QuoteResponse() {
        this.buffer = new UnsafeBuffer(ByteBuffer.allocateDirect(SIZE));
    }

    public UnsafeBuffer getBuffer() {
        return buffer;
    }

    public int getInstrumentId() {
        return buffer.getInt(0);
    }

    public void setInstrumentId(int instrumentId) {
        buffer.putInt(0, instrumentId);
    }

    public boolean getAccepted() {
        return buffer.getByte(4) != 0;
    }

    public void setAccepted(boolean accepted) {
        buffer.putByte(4, (byte)(accepted ? 1 : 0));
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append("[instrument: ").
                append(getInstrumentId()).
                append(", accepted: ").
                append(getAccepted()).
                append("]").
                toString();
    }
}
