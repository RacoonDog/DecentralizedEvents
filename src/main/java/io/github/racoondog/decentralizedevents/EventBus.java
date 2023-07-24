package io.github.racoondog.decentralizedevents;

import io.github.racoondog.decentralizedevents.events.Event;
import io.github.racoondog.decentralizedevents.exceptions.EventTypeNotRegisteredException;
import io.github.racoondog.decentralizedevents.listeners.Listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Technically optional for this event system. Included because it's handy to have a central directory for subscribing/unsubscribing listeners.
 */
public class EventBus {
    private final Map<Class<? extends Event<?, ?>>, Event<?, ?>> eventMap = new ConcurrentHashMap<>();

    /**
     * Register event type. Required for all methods which take a {@link Class} as an argument.
     */
    @SuppressWarnings("unchecked")
    public <E extends Event<?, ?>> void registerEventType(E event) {
        eventMap.put((Class<? extends Event<?, ?>>) event.getClass(), event);
    }

    /**
     * Post {@link Event} with itself as the listened object.
     */
    public <E extends Event<E, ?>> void post(E event) {
        event.call(event);
    }

    /**
     * Post {@link Event} with itself as the listened object. Prefer using {@link EventBus#post(Event)} over this if possible.
     */
    public <E extends Event<E, ?>> void post(Class<E> eventClass) {
        post(get(eventClass));
    }

    /**
     * Post {@link Event} with {@code object} parameter as the listened object.
     */
    public <T, E extends Event<T, ?>> void post(E event, T object) {
        event.call(object);
    }

    /**
     * Post {@link Event} with {@code object} parameter as the listened object. Prefer using {@link EventBus#post(Event, Object)} over this if possible.
     */
    public <T, E extends Event<T, ?>> void post(Class<E> eventClass, T object) {
        post(get(eventClass), object);
    }

    /**
     * Subscribe {@link Listener} to the given {@link Event}.
     */
    public <T, E extends Event<T, Listener<T>>> void subscribe(E event, Listener<T> listener) {
        event.subscribe(listener);
    }

    /**
     * Subscribe {@link Listener} to the given {@link Event}. Prefer using {@link EventBus#subscribe(Event, Listener)} over this if possible.
     */
    public <T, E extends Event<T, Listener<T>>> void subscribe(Class<E> eventClass, Listener<T> listener) {
        subscribe(get(eventClass), listener);
    }

    /**
     * Unsubscribe {@link Listener} to the given {@link Event}.
     */
    public <T, E extends Event<T, Listener<T>>> void unsubscribe(E event, Listener<T> listener) {
        event.unsubscribe(listener);
    }

    /**
     * Unsubscribe {@link Listener} to the given {@link Event}. Prefer using {@link EventBus#unsubscribe(Event, Listener)} over this if possible.
     */
    public <T, E extends Event<T, Listener<T>>> void unsubscribe(Class<E> eventClass, Listener<T> listener) {
        unsubscribe(get(eventClass), listener);
    }


    // Helper methods

    @SuppressWarnings("unchecked")
    private <E extends Event<?, ?>> E get(Class<E> eventClass) {
        E event = (E) eventMap.get(eventClass);
        if (event == null) throw new EventTypeNotRegisteredException(eventClass);
        return event;
    }
}
