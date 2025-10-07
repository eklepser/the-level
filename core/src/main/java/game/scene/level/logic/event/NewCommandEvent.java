package game.scene.level.logic.event;

import game.scene.level.logic.command.Command;

public class NewCommandEvent extends LevelEvent {
    public final Command command;

    public NewCommandEvent(Command command) {
        this.command = command;
    }
}
