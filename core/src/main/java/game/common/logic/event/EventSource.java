package game.common.logic.event;

import java.util.ArrayList;
import java.util.List;

public abstract class EventSource<T extends Enum<T>> {
    private final List<EventListener<T>> subscribers;

    public EventSource() {
        subscribers = new ArrayList<>();
    }

    public void subscribe(EventListener<T> listener) {
        if (!subscribers.contains(listener)) {
            subscribers.add(listener);
        }
    }

    public void unsubscribe(EventListener<T> listener) {
        subscribers.remove(listener);
    }

    public void notifySubscribers(T event) {
        for (EventListener<T> listener : new ArrayList<>(subscribers)) {
            listener.onEvent(event);
        }
    }
}
