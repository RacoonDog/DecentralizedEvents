package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.listeners.Listener;

public interface Event<T, L extends Listener<T>> {
    void call(T event);

    void subscribe(L listener);

    void unsubscribe(L listener);
}
