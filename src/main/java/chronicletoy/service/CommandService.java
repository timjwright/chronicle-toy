package chronicletoy.service;

import chronicletoy.model.input.Command;

public interface CommandService {
    void bootstrap();
    boolean pollCommand(Command buffer);
}
