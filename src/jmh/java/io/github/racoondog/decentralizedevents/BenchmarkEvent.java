package io.github.racoondog.decentralizedevents;

import io.github.racoondog.decentralizedevents.events.CopyOnWriteEvent;
import org.openjdk.jmh.infra.Blackhole;

public class BenchmarkEvent extends CopyOnWriteEvent<Blackhole> {}
