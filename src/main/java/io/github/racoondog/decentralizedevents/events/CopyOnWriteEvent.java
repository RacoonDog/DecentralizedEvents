package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.listeners.Listener;

@SuppressWarnings("unchecked")
public abstract class CopyOnWriteEvent<T, L extends Listener<T>> implements Event<T, L> {
    private Listener<T>[] listeners = new Listener[0];

    protected CopyOnWriteEvent() {}

    @Override
    public Listener<T>[] getListeners() {
        return listeners;
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
        int index = -1;
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == listener) {
                index = i;
                break;
            }
        }
        if (index == -1) return;
        int newLen = listeners.length - 1;
        Listener<T>[] newListeners = new Listener[newLen];
        System.arraycopy(listeners, 0, newListeners, 0, index);
        System.arraycopy(listeners, index + 1, newListeners, index, newLen - index);
        listeners = newListeners;
    }
}
