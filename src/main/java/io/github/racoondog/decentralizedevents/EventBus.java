package io.github.racoondog.decentralizedevents;

import io.github.racoondog.decentralizedevents.events.Event;
import io.github.racoondog.decentralizedevents.exceptions.EventTypeNotRegisteredException;
import io.github.racoondog.decentralizedevents.listeners.Listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {
    private final Map<Class<? extends Event<?, ?>>, Event<?, ?>> eventMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <E extends Event<?, ?>> void registerEventType(E event) {
        eventMap.put((Class<? extends Event<?, ?>>) event.getClass(), event);
    }

    public <E extends Event<E, ?>> void post(E event) {
        event.call(event);
    }

    public <E extends Event<E, ?>> void post(Class<E> eventClass) {
        post(get(eventClass));
    }

    public <T, E extends Event<T, ?>> void post(E event, T object) {
        event.call(object);
    }

    public <T, E extends Event<T, ?>> void post(Class<E> eventClass, T object) {
        post(get(eventClass), object);
    }

    public <T, E extends Event<T, Listener<T>>> void subscribe(Class<E> eventClass, Listener<T> listener) {
        subscribe(get(eventClass), listener);
    }

    public <T, E extends Event<T, Listener<T>>> void subscribe(E event, Listener<T> listener) {
        event.subscribe(listener);
    }

    public <T, E extends Event<T, Listener<T>>> void unsubscribe(Class<E> eventClass, Listener<T> listener) {
        unsubscribe(get(eventClass), listener);
    }

    public <T, E extends Event<T, Listener<T>>> void unsubscribe(E event, Listener<T> listener) {
        event.unsubscribe(listener);
    }

    // Helper methods

    @SuppressWarnings("unchecked")
    private <E extends Event<?, ?>> E get(Class<E> eventClass) {
        E event = (E) eventMap.get(eventClass);
        if (event == null) throw new EventTypeNotRegisteredException(eventClass);
        return event;
    }
}
