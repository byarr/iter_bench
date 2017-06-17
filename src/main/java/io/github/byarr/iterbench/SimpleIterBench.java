package io.github.byarr.iterbench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class SimpleIterBench {

    @Param({"1", "100", "10000", "1000000"})
    private int numIterations;

    @Benchmark
    public void forwardIter(final Blackhole bh) {
        for (int i = 0; i < numIterations; i++) {
            bh.consume(i);
        }
    }

    @Benchmark
    public void backwardIter(final Blackhole bh) {
        for (int i = numIterations-1; i >= 0; i--) {
            bh.consume(i);
        }
    }

}
