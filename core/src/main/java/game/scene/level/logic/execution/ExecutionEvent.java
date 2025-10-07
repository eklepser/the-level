package game.scene.level.logic.execution;

import game.common.logic.event.GameEvent;

public final class ExecutionEvent extends GameEvent {
    public final int currentLine;

    public ExecutionEvent(int currentLine) {
        this.currentLine = currentLine;
    }
}

