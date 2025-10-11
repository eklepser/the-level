package game.scene.common.logic.event;

import java.util.ArrayList;
import java.util.List;

public abstract class EventSource<T extends GameEvent> {
    private final List<EventListener<T>> subscribers;

    public EventSource() {
        subscribers = new ArrayList<>();
    }

    public void subscribe(EventListener<T> listener) {
        if (!subscribers.contains(listener)) {
            subscribers.add(listener);
            listener.onSubscribe();
        }
    }

    public void unsubscribe(EventListener<T> listener) {
        subscribers.remove(listener);
    }

    public void fire(T event) {
        for (EventListener<T> listener : new ArrayList<>(subscribers)) {
            listener.onEvent(event);
        }
    }
}
