package chronicletoy.service.spoof;

import chronicletoy.Universe;
import chronicletoy.controller.Controller;
import chronicletoy.model.input.Command;
import chronicletoy.model.input.CommandType;
import chronicletoy.service.CommandService;

import java.util.LinkedList;

public class LinkedListBackedCommandService implements CommandService, Controller {

    private LinkedList<Command> commands = new LinkedList<>();

    @Override
    public void bootstrap() {
        synchronized (commands) {
            commands.clear();
            Universe.bootstrap(this);
        }
    }

    @Override
    public void start(int instrumentId) {
        Command command = new Command();
        command.setCommand(CommandType.START, instrumentId);
        offer(command);
    }

    @Override
    public void stop(int instrumentId) {
        Command command = new Command();
        command.setCommand(CommandType.STOP, instrumentId);
        offer(command);
    }

    @Override
    public void setParameters(int instrumentId, int spread) {
        Command command = new Command();
        command.setCommand(CommandType.PARAMETERS, instrumentId);
        command.getParameterData().setSpread(spread);
        offer(command);
    }

    @Override
    public void staticData(int instrumentId, int underlier, int scale) {
        Command command = new Command();
        command.setCommand(CommandType.STATIC, instrumentId);
        command.getStaticData().getUnderlying(underlier);
        command.getStaticData().setScale(scale);
        offer(command);
    }

    public void offer(Command command) {
        synchronized (commands) {
            System.out.println("Queining " + command);
            commands.offer(command);
        }
    }

    @Override
    public boolean pollCommand(Command buffer) {
        synchronized (commands) {
            Command c = commands.poll();
            if ( c != null ) {
                c.writeTo(buffer);
                System.out.println("Polling command: " + c);
                return true;
            } else {
                return false;
            }
        }
    }
}
