package io.github.racoondog.decentralizedevents;

import io.github.racoondog.decentralizedevents.listeners.Listener;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 8, time = 5)
@Measurement(iterations = 4, time = 5)
public class BenchmarkCaller implements Listener<Blackhole> {
    private static final int ITERATIONS = 100_000;

    private static final BenchmarkEvent EVENT = new BenchmarkEvent();

    @Setup
    public void setup() {
        EVENT.subscribe(this);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public void bench(Blackhole bh) {
        for (int i = 0; i < ITERATIONS; i++) {
            EVENT.call(bh);
        }
    }

    @Override
    public void listen(Blackhole event) {
        event.consume(Integer.bitCount(Integer.parseInt("123")));
    }
}
