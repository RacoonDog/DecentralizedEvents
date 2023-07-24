package io.github.racoondog.decentralizedevents;

import io.github.racoondog.decentralizedevents.events.CopyOnWriteEvent;
import io.github.racoondog.decentralizedevents.listeners.Listener;
import org.openjdk.jmh.infra.Blackhole;

public class BenchmarkEvent extends CopyOnWriteEvent<Blackhole, Listener<Blackhole>> {}
