package io.github.racoondog.decentralizedevents.exceptions;

import io.github.racoondog.decentralizedevents.events.Event;

public class EventTypeNotRegisteredException extends RuntimeException {
    public EventTypeNotRegisteredException(Class<? extends Event<?, ?>> eventClass) {
        super("Event type '" + eventClass.getName() + "' was not registered to the event bus.");
    }
}
