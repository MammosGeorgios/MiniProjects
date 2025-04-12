package example;


import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class ArrayAndListCreationTests {

//    Benchmark                                                                     Mode  Cnt         Score         Error  Units
//    ArrayAndListCreationTests.array10                                             avgt   50         0,540 ±       0,008  ns/op
//    ArrayAndListCreationTests.array100                                            avgt   50        63,108 ±       1,415  ns/op
//    ArrayAndListCreationTests.array100000                                         avgt   50     56945,934 ±     894,691  ns/op
//    ArrayAndListCreationTests.array1000000                                        avgt   50    615005,251 ±   16117,544  ns/op
//    ArrayAndListCreationTests.list1000000_withStartingSize_withIndexAdding        avgt   50  14012805,113 ± 1045919,127  ns/op
//    ArrayAndListCreationTests.list1000000_withStartingSize_withoutIndexAdding     avgt   50  12637858,171 ±  968762,535  ns/op
//    ArrayAndListCreationTests.list1000000_withoutStartingSize_withIndexAdding     avgt   50  21237093,832 ± 1681358,796  ns/op
//    ArrayAndListCreationTests.list1000000_withoutStartingSize_withoutIndexAdding  avgt   50  19977113,890 ± 1867747,668  ns/op
//    ArrayAndListCreationTests.list100000_withStartingSize_withIndexAdding         avgt   50    238427,110 ±    1882,337  ns/op
//    ArrayAndListCreationTests.list100000_withStartingSize_withoutIndexAdding      avgt   50    463747,160 ±   19203,644  ns/op
//    ArrayAndListCreationTests.list100000_withoutStartingSize_withIndexAdding      avgt   50    549996,676 ±   17895,283  ns/op
//    ArrayAndListCreationTests.list100000_withoutStartingSize_withoutIndexAdding   avgt   50    396837,801 ±    4661,340  ns/op
//    ArrayAndListCreationTests.list100_withStartingSize_withIndexAdding            avgt   50       173,681 ±       0,599  ns/op
//    ArrayAndListCreationTests.list100_withStartingSize_withoutIndexAdding         avgt   50       218,574 ±       1,885  ns/op
//    ArrayAndListCreationTests.list100_withoutStartingSize_withIndexAdding         avgt   50       326,652 ±       6,056  ns/op
//    ArrayAndListCreationTests.list100_withoutStartingSize_withoutIndexAdding      avgt   50       363,083 ±       7,075  ns/op
//    ArrayAndListCreationTests.list10_withStartingSize_withIndexAdding             avgt   50        15,785 ±       0,113  ns/op
//    ArrayAndListCreationTests.list10_withStartingSize_withoutIndexAdding          avgt   50        30,203 ±       0,198  ns/op
//    ArrayAndListCreationTests.list10_withoutStartingSize_withIndexAdding          avgt   50        38,283 ±       0,661  ns/op
//    ArrayAndListCreationTests.list10_withoutStartingSize_withoutIndexAdding       avgt   50        23,189 ±       0,129  ns/op


    @Benchmark
    public long array10() {
        final int size = 10;

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array.length;
    }

    @Benchmark
    public long array100() {
        final int size = 100;

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array.length;
    }

    @Benchmark
    public long array100000() {
        final int size = 100000;

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array.length;
    }

    @Benchmark
    public long array1000000() {
        final int size = 1000000;

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array.length;
    }

    @Benchmark
    public long list10_withoutStartingSize_withoutIndexAdding() {
        final int size = 10;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list10_withoutStartingSize_withIndexAdding() {
        final int size = 10;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }
    @Benchmark
    public long list10_withStartingSize_withoutIndexAdding() {
        final int size = 10;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list10_withStartingSize_withIndexAdding() {
        final int size = 10;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }

    @Benchmark
    public long list100_withoutStartingSize_withoutIndexAdding() {
        final int size = 100;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list100_withoutStartingSize_withIndexAdding() {
        final int size = 100;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }
    @Benchmark
    public long list100_withStartingSize_withoutIndexAdding() {
        final int size = 100;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list100_withStartingSize_withIndexAdding() {
        final int size = 100;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }

    @Benchmark
    public long list100000_withoutStartingSize_withoutIndexAdding() {
        final int size = 100000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list100000_withoutStartingSize_withIndexAdding() {
        final int size = 100000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }
    @Benchmark
    public long list100000_withStartingSize_withoutIndexAdding() {
        final int size = 100000;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list100000_withStartingSize_withIndexAdding() {
        final int size = 100000;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }
    @Benchmark
    public long list1000000_withoutStartingSize_withoutIndexAdding() {
        final int size = 1000000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list1000000_withoutStartingSize_withIndexAdding() {
        final int size = 1000000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }
    @Benchmark
    public long list1000000_withStartingSize_withoutIndexAdding() {
        final int size = 1000000;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.size();
    }

    @Benchmark
    public long list1000000_withStartingSize_withIndexAdding() {
        final int size = 1000000;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i, i);
        }
        return list.size();
    }

}
