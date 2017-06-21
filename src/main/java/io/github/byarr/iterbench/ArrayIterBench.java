package io.github.byarr.iterbench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Random;

@State(Scope.Benchmark)
public class ArrayIterBench {

    @Param({"10", "100", "1000", "10000"})
    private int numIterations;
    private int[] ints;

    @Setup
    public void init() {
        final Random random = new Random();
        ints = new int[numIterations];
        for (int i = 0; i < numIterations; i++) {
            ints[i] = random.nextInt();
        }
    }

    @Benchmark
    public void forwardIter(final Blackhole bh) {
        for (int i = 0; i < ints.length; i++) {
            bh.consume(ints[i]);
        }
    }

    @Benchmark
    public void backwardIter(final Blackhole bh) {
        for (int i = ints.length - 1; i >= 0; i--) {
            bh.consume(ints[i]);
        }
    }

    @Benchmark
    public void enhancedFor(final Blackhole bh) {
        for (int i : ints) {
            bh.consume(i);
        }
    }

    @Benchmark
    public void stream(final Blackhole bh) {
        Arrays.stream(ints).forEach(bh::consume);
    }

}
