package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.listeners.Listener;

public interface Event<E, L extends Listener<E>> {
    Listener[] getListeners();

    default void call(E event) {
        for (Listener listener : getListeners()) listener.listen(event);
    }

    void subscribe(L listener);

    void unsubscribe(L listener);
}
