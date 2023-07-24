package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.listeners.Listener;

/**
 * @param <T> Object type supplied within the listener.
 * @param <L> Listener type.
 */
public interface Event<T, L extends Listener<T>> {
    void call(T event);

    void subscribe(L listener);

    void unsubscribe(L listener);
}
