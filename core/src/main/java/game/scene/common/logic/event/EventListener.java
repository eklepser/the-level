package game.scene.common.logic.event;

public interface EventListener<T extends GameEvent> {
    void onEvent(T event);
    default void onSubscribe() { }
}
