package game.scene.level.logic.event;

import game.scene.level.logic.command.Command;

public final class LevelTurnEvent extends LevelEvent {
    public final Command command;

    public LevelTurnEvent(Command command) {
        this.command = command;
    }
}
