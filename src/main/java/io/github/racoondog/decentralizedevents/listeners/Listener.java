package io.github.racoondog.decentralizedevents.listeners;

@FunctionalInterface
public interface Listener<T> {
    void listen(T event);
}
