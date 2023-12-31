package io.github.racoondog.decentralizedevents.events;

import io.github.racoondog.decentralizedevents.ArrayUtils;
import io.github.racoondog.decentralizedevents.listeners.Listener;

import java.util.Arrays;

/**
 * Thread-unsafe {@link Event} implementation, array sized logarithmically to limit resizes.
 * @param <T> Object type supplied within the listener.
 */
public abstract class LogSizeArrayEvent<T> implements Event<T> {
    @SuppressWarnings("unchecked")
    private Listener<T>[] listeners = new Listener[0];
    private int size = 0;

    @Override
    public void call(T event) {
        for (int i = 0; i < size; i++) {
            listeners[i].listen(event);
        }
    }

    @Override
    public void subscribe(Listener<T> listener) {
        if (ArrayUtils.contains(listeners, size, listener)) return;

        int len = listeners.length;

        if (size >= len) {
            len = len == 0 ? 4 : len + (len >> 1);
            listeners = Arrays.copyOf(listeners, len);
        }

        listeners[size++] = listener;
    }

    @Override
    public void unsubscribe(Listener<T> listener) {
        int index = ArrayUtils.search(listeners, size, listener);
        if (index == -1) return;

        int numMoved = size - index - 1;
        if (numMoved > 0) System.arraycopy(listeners, index + 1, listeners, index, numMoved);
        listeners[--size] = null;
    }
}
