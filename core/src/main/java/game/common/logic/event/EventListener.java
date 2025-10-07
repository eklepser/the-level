package game.common.logic.event;

public interface EventListener<T extends GameEvent> {
    void onEvent(T event);
}
