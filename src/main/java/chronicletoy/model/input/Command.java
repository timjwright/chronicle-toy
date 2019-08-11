package chronicletoy.model.input;

import chronicletoy.model.BidAsk;
import chronicletoy.model.ParameterData;
import chronicletoy.model.StaticData;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class Command {
    public final static int SIZE = BidAsk.SIZE + 8;

    private final MutableDirectBuffer buffer;
    private final StaticData staticData = new StaticData();
    private final ParameterData parameterData = new ParameterData();

    public Command() {
        this.buffer = new UnsafeBuffer(ByteBuffer.allocateDirect(SIZE));
        staticData.wrap(buffer, 8);
        parameterData.wrap(buffer, 8);
    }
    public void writeTo(Command other) {
        other.buffer.putBytes(0, this.buffer, 0, SIZE);
    }

    public MutableDirectBuffer getBuffer() {
        return buffer;
    }

    public int getInstrumentId() {
        return buffer.getInt(0);
    }

    public CommandType getCommand() {
        return CommandType.values()[buffer.getInt(4)];
    }

    public void setCommand(CommandType commandType, int instrumentId) {
        buffer.putInt(4, commandType.ordinal());
        buffer.putInt(0, instrumentId);
    }

    public StaticData getStaticData() {
        return staticData;
    }

    public ParameterData getParameterData() {
        return parameterData;
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append("[instrument: ").
                append(getInstrumentId()).
                append(", command: ").
                append(getCommand()).
                append(", ").
                append(getCommandDesc()).
                append("]").
                toString();
    }

    private String getCommandDesc() {
        switch(getCommand()) {
            case START: return "-";
            case STOP: return "-";
            case STATIC: return getStaticData().toString();
            case PARAMETERS: return getParameterData().toString();
            default: return "?";
        }
    }
}
