package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.listeners.Listener;

/**
 * @param <T> Object type supplied within the listener.
 */
public interface Event<T> {
    void call(T event);

    void subscribe(Listener<T> listener);

    void unsubscribe(Listener<T> listener);
}
