package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.ArrayUtils;
import io.github.racoondog.decentralizedevents.listeners.Listener;

import java.util.Arrays;

public abstract class LogSizeArrayEvent<T, L extends Listener<T>> implements Event<T, L> {
    @SuppressWarnings("unchecked")
    private Listener<T>[] listeners = new Listener[0];
    private int size = 0;

    protected LogSizeArrayEvent() {}

    @Override
    public Listener<T>[] getListeners() {
        return listeners;
    }

    @Override
    public void subscribe(L listener) {
        growIfNeeded();
        listeners[size++] = listener;
    }

    @Override
    public void unsubscribe(L listener) {
        int index = ArrayUtils.search(listeners, size, listener);
        if (index == -1) return;

        int numMoved = size - index - 1;
        if (numMoved > 0) System.arraycopy(listeners, index + 1, listeners, index, numMoved);
        listeners[--size] = null;
    }

    private void growIfNeeded() {
        int len = listeners.length;
        if (size >= len) {
            len = len + (len >> 1);
            listeners = Arrays.copyOf(listeners, len);
        }
    }
}
