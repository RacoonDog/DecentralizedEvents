package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.ArrayUtils;
import io.github.racoondog.decentralizedevents.listeners.Listener;

@SuppressWarnings("unchecked")
public abstract class CopyOnWriteEvent<T, L extends Listener<T>> implements Event<T, L> {
    private Listener<T>[] listeners = new Listener[0];

    @Override
    public void call(T event) {
        for (Listener<T> listener : listeners) listener.listen(event);
    }

    @Override
    public void subscribe(L listener) {
        Listener<T>[] newListeners = new Listener[listeners.length + 1];
        System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
        newListeners[listeners.length] = listener;
        listeners = newListeners;
    }

    @Override
    public void unsubscribe(L listener) {
        int index = ArrayUtils.search(listeners, listener);
        if (index == -1) return;

        int newLen = listeners.length - 1;
        Listener<T>[] newListeners = new Listener[newLen];
        if (index > 0) System.arraycopy(listeners, 0, newListeners, 0, index);
        System.arraycopy(listeners, index + 1, newListeners, index, newLen - index);
        listeners = newListeners;
    }
}
