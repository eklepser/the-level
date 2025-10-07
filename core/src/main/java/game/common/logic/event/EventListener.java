package game.common.logic.event;

public interface EventListener<T extends Enum<T>> {
    void onEvent(T event);
}
