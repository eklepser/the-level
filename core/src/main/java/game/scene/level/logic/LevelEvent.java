package game.scene.level.logic;

import game.common.logic.event.EventType;
import game.common.logic.event.GameEvent;

public class LevelEvent extends GameEvent {
    public final EventType type;
    public final Object value;

    public LevelEvent(EventType type) {
        this.type = type;
        value = null;
    }

    public LevelEvent(EventType type, Object value) {
        this.type = type;
        this.value = value;
    }
}


