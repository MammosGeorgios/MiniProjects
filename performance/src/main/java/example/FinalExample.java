package example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class FinalExample {



//    Benchmark                           Mode  Cnt  Score   Error  Units
//    FinalExample.concatFinalStrings     avgt    5  0,384 ± 0,015  ns/op
//    FinalExample.concatNonFinalStrings  avgt    5  4,335 ± 0,093  ns/op

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public static String concatNonFinalStrings() {
        String x = "x";
        String y = "y";
        return x + y;
    }


    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public static String concatFinalStrings() {
        final String x = "x";
        final String y = "y";
        return x + y;
    }
}
