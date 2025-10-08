package game.scene.level.logic.event;

import game.scene.level.logic.execution.TranslationResult;

public class ExecutionStartEvent extends LevelEvent {
    public final TranslationResult translationResult;

    public ExecutionStartEvent(TranslationResult translationResult) {
        this.translationResult = translationResult;
    }
}
