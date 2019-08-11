package chronicletoy.model;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

public class ParameterData {
    public final static int SIZE = Integer.BYTES * 4;

    private final MutableDirectBuffer buffer = new UnsafeBuffer();

    public int wrap(MutableDirectBuffer buffer, int offset) {
        this.buffer.wrap(buffer, offset, SIZE);
        return offset + SIZE;
    }

    public void writeTo(ParameterData other) {
        other.buffer.putBytes(0, this.buffer, 0, SIZE);
    }

    public int getSpread() {
        return buffer.getInt(0);
    }

    public void setSpread(int spread) {
        buffer.putInt(0, spread);
    }


    @Override
    public String toString() {
        return new StringBuilder().
                append("spread: ").
                append(getSpread()).
                toString();
    }
}

