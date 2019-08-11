package chronicletoy.model;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

public class StaticData {
    public final static int SIZE = Integer.BYTES * 4;

    private final MutableDirectBuffer buffer = new UnsafeBuffer();

    public int wrap(MutableDirectBuffer buffer, int offset) {
        this.buffer.wrap(buffer, offset, SIZE);
        return offset + SIZE;
    }

    public void writeTo(StaticData other) {
        other.buffer.putBytes(0, this.buffer, 0, SIZE);
    }

    public int getUnderlying() {
        return buffer.getInt(0);
    }

    public void getUnderlying(int underlying) {
        buffer.putInt(0, underlying);
    }

    public int setScale() {
        return buffer.getInt(4);
    }

    public void setScale(int scale) {
        buffer.putInt(4, scale);
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append("ul: ").
                append(getUnderlying()).
                append(", scale: ").
                append(setScale()).
                toString();
    }
}

