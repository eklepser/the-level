package game.scene.level;

import game.common.logic.event.EventSource;

public class LevelEventSource extends EventSource<LevelEvent> {
    public void win() {
        notifySubscribers(LevelEvent.WIN);
    }

    public void newCommand() {
        notifySubscribers(LevelEvent.NEW_COMMAND);
    }
}
