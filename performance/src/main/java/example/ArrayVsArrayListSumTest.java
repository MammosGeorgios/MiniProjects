package example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class ArrayVsArrayListSumTest {


//    Benchmark                                                                 Mode  Cnt      Score      Error  Units
//    ArrayVsArrayListSumTest.sumArray100000_enhancedLoop                       avgt   15  25743,316 ±  487,258  ns/op
//    ArrayVsArrayListSumTest.sumArray100000_loop                               avgt   15  26082,585 ±  298,431  ns/op
//    ArrayVsArrayListSumTest.sumArray10000_enhancedLoop                        avgt   15   2583,973 ±   23,150  ns/op
//    ArrayVsArrayListSumTest.sumArray10000_loop                                avgt   15   2592,412 ±   28,101  ns/op
//    ArrayVsArrayListSumTest.sumArray1000_enhancedLoop                         avgt   15    257,826 ±    5,581  ns/op
//    ArrayVsArrayListSumTest.sumArray1000_loop                                 avgt   15    260,999 ±    6,650  ns/op
//    ArrayVsArrayListSumTest.sumArray100_enhancedLoop                          avgt   15     25,432 ±    0,356  ns/op
//    ArrayVsArrayListSumTest.sumArray100_loop                                  avgt   15     25,201 ±    0,482  ns/op
//    ArrayVsArrayListSumTest.sumArray10_enhancedLoop                           avgt   15      4,708 ±    0,049  ns/op
//    ArrayVsArrayListSumTest.sumArray10_loop                                   avgt   15      4,377 ±    0,072  ns/op
//    ArrayVsArrayListSumTest.sumList100000WithoutInitialCapacity_enhancedLoop  avgt   15  69678,462 ± 1119,821  ns/op
//    ArrayVsArrayListSumTest.sumList100000WithoutInitialCapacity_iterator      avgt   15  69674,969 ± 1591,090  ns/op
//    ArrayVsArrayListSumTest.sumList100000WithoutInitialCapacity_loop          avgt   15  67257,420 ±  774,114  ns/op
//    ArrayVsArrayListSumTest.sumList100000_enhancedLoop                        avgt   15  69966,426 ± 1204,697  ns/op
//    ArrayVsArrayListSumTest.sumList100000_iterator                            avgt   15  69854,542 ± 1523,540  ns/op
//    ArrayVsArrayListSumTest.sumList100000_loop                                avgt   15  67297,638 ± 1571,255  ns/op
//    ArrayVsArrayListSumTest.sumList10000WithoutInitialCapacity_enhancedLoop   avgt   15   6334,728 ±  123,206  ns/op
//    ArrayVsArrayListSumTest.sumList10000WithoutInitialCapacity_iterator       avgt   15   6342,401 ±   92,429  ns/op
//    ArrayVsArrayListSumTest.sumList10000WithoutInitialCapacity_loop           avgt   15   6361,250 ±   75,639  ns/op
//    ArrayVsArrayListSumTest.sumList10000_enhancedLoop                         avgt   15   6440,906 ±  125,464  ns/op
//    ArrayVsArrayListSumTest.sumList10000_iterator                             avgt   15   6386,798 ±  117,349  ns/op
//    ArrayVsArrayListSumTest.sumList10000_loop                                 avgt   15   6482,275 ±  161,257  ns/op
//    ArrayVsArrayListSumTest.sumList1000WithoutInitialCapacity_enhancedLoop    avgt   15    649,733 ±   15,925  ns/op
//    ArrayVsArrayListSumTest.sumList1000WithoutInitialCapacity_iterator        avgt   15    644,106 ±    5,331  ns/op
//    ArrayVsArrayListSumTest.sumList1000WithoutInitialCapacity_loop            avgt   15    646,945 ±    7,108  ns/op
//    ArrayVsArrayListSumTest.sumList1000_enhancedLoop                          avgt   15    648,278 ±    7,156  ns/op
//    ArrayVsArrayListSumTest.sumList1000_iterator                              avgt   15    648,683 ±    8,620  ns/op
//    ArrayVsArrayListSumTest.sumList1000_loop                                  avgt   15    642,753 ±    4,977  ns/op
//    ArrayVsArrayListSumTest.sumList100WithoutInitialCapacity_enhancedLoop     avgt   15     62,703 ±    0,586  ns/op
//    ArrayVsArrayListSumTest.sumList100WithoutInitialCapacity_iterator         avgt   15     62,862 ±    0,831  ns/op
//    ArrayVsArrayListSumTest.sumList100WithoutInitialCapacity_loop             avgt   15     62,562 ±    0,677  ns/op
//    ArrayVsArrayListSumTest.sumList100_enhancedLoop                           avgt   15     62,645 ±    0,632  ns/op
//    ArrayVsArrayListSumTest.sumList100_iterator                               avgt   15     62,435 ±    0,532  ns/op
//    ArrayVsArrayListSumTest.sumList100_loop                                   avgt   15     62,562 ±    0,710  ns/op
//    ArrayVsArrayListSumTest.sumList10WithoutInitialCapacity_enhancedLoop      avgt   15     11,573 ±    0,196  ns/op
//    ArrayVsArrayListSumTest.sumList10WithoutInitialCapacity_iterator          avgt   15     11,630 ±    0,151  ns/op
//    ArrayVsArrayListSumTest.sumList10WithoutInitialCapacity_loop              avgt   15      9,443 ±    0,126  ns/op
//    ArrayVsArrayListSumTest.sumList10_enhancedLoop                            avgt   15     11,865 ±    0,774  ns/op
//    ArrayVsArrayListSumTest.sumList10_iterator                                avgt   15     12,199 ±    0,763  ns/op
//    ArrayVsArrayListSumTest.sumList10_loop                                    avgt   15      9,436 ±    0,157  ns/op


// Loop and enhanced loops seem to be the same for Arrays and ArrayLists (however, for arrayLists with 100000 size, simple loop seems slightly faster)
// Setting the initial capacity early on for List didn't affect performance (should test the list generation separately)



/* Comparing array with ArrayList at size 10
        sumList10_enhancedLoop                            avgt   15     11,865 ±    0,774  ns/op
        sumList10_iterator                                avgt   15     12,199 ±    0,763  ns/op
        sumList10_loop                                    avgt   15      9,436 ±    0,157  ns/op
        sumArray10_enhancedLoop                           avgt   15      4,708 ±    0,049  ns/op
        sumArray10_loop                                   avgt   15      4,377 ±    0,072  ns/op

        - Array seems 2~3 times faster, but insignificant time difference overall
        - Also, we can perhaps see the cost of setting up iterator/enhanced loop
*/

/* Comparing array with ArrayList at size 100000

        sumList100000_enhancedLoop                        avgt   15  69966,426 ± 1204,697  ns/op
        sumList100000_iterator                            avgt   15  69854,542 ± 1523,540  ns/op
        sumList100000_loop                                avgt   15  67297,638 ± 1571,255  ns/op
        sumArray100000_enhancedLoop                       avgt   15  25743,316 ±  487,258  ns/op
        sumArray100000_loop                               avgt   15  26082,585 ±  298,431  ns/op

        - Array seems ~2.5 times faster
        - We see the same difference between List simple loop and enhanced/iterator. Seems the cost of enhancedLoop/Iterator to be a relatively consistent value
*/

    private int[] array10 = new int[10];
    private int[] array100 = new int[100];
    private int[] array1000 = new int[1000];
    private int[] array10000 = new int[10000];
    private int[] array100000 = new int[100000];

    private List<Integer> list10 = new ArrayList<>(10);
    private List<Integer> list100 = new ArrayList<>(100);
    private List<Integer> list1000 = new ArrayList<>(1000);
    private List<Integer> list10000 = new ArrayList<>(10000);
    private List<Integer> list100000 = new ArrayList<>(100000);

    private List<Integer> listWithoutInitialCapacity10 = new ArrayList<>();
    private List<Integer> listWithoutInitialCapacity100 = new ArrayList<>();
    private List<Integer> listWithoutInitialCapacity1000 = new ArrayList<>();
    private List<Integer> listWithoutInitialCapacity10000 = new ArrayList<>();
    private List<Integer> listWithoutInitialCapacity100000 = new ArrayList<>();


    @Setup
    public void setup() {

        Random rand = new Random(1234);

        for (int i = 0; i < 10; i++) {
            array10[i] = rand.nextInt();
            list10.add(array10[i]);
            listWithoutInitialCapacity10.add(array10[i]);
        }

        for (int i = 0; i < 100; i++) {
            array100[i] = rand.nextInt();
            list100.add(array100[i]);
            listWithoutInitialCapacity100.add(array100[i]);
        }

        for (int i = 0; i < 1000; i++) {
            array1000[i] = rand.nextInt();
            list1000.add(array1000[i]);
            listWithoutInitialCapacity1000.add(array1000[i]);
        }

        for (int i = 0; i < 10000; i++) {
            array10000[i] = rand.nextInt();
            list10000.add(array10000[i]);
            listWithoutInitialCapacity10000.add(array10000[i]);
        }

        for (int i = 0; i < 100000; i++) {
            array100000[i] = rand.nextInt();
            list100000.add(array100000[i]);
            listWithoutInitialCapacity100000.add(array100000[i]);
        }

    }


    //SIZE 10

    @Benchmark
    public long sumArray10_loop() {
        long sum = 0;
        for (int i = 0; i < array10.length; i++) {
            sum += array10[i];
        }
        return sum;
    }

    @Benchmark
    public long sumArray10_enhancedLoop() {
        long sum = 0;
        for (int num : array10) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public long sumList10_loop() {
        long sum = 0;
        for (int i = 0; i < list10.size(); i++) {
            sum += list10.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList10_enhancedLoop() {
        long sum = 0;
        for (Integer integer : list10) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList10_iterator() {
        long sum = 0;

        Iterator<Integer> interator = list10.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    @Benchmark
    public long sumList10WithoutInitialCapacity_loop() {
        long sum = 0;
        for (int i = 0; i < listWithoutInitialCapacity10.size(); i++) {
            sum += listWithoutInitialCapacity10.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList10WithoutInitialCapacity_enhancedLoop() {
        long sum = 0;
        for (Integer integer : listWithoutInitialCapacity10) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList10WithoutInitialCapacity_iterator() {
        long sum = 0;

        Iterator<Integer> interator = listWithoutInitialCapacity10.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    //SIZE 100

    @Benchmark
    public long sumArray100_loop() {
        long sum = 0;
        for (int i = 0; i < array100.length; i++) {
            sum += array100[i];
        }
        return sum;
    }

    @Benchmark
    public long sumArray100_enhancedLoop() {
        long sum = 0;
        for (int num : array100) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public long sumList100_loop() {
        long sum = 0;
        for (int i = 0; i < list100.size(); i++) {
            sum += list100.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList100_enhancedLoop() {
        long sum = 0;
        for (Integer integer : list100) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList100_iterator() {
        long sum = 0;

        Iterator<Integer> interator = list100.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    @Benchmark
    public long sumList100WithoutInitialCapacity_loop() {
        long sum = 0;
        for (int i = 0; i < listWithoutInitialCapacity100.size(); i++) {
            sum += listWithoutInitialCapacity100.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList100WithoutInitialCapacity_enhancedLoop() {
        long sum = 0;
        for (Integer integer : listWithoutInitialCapacity100) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList100WithoutInitialCapacity_iterator() {
        long sum = 0;

        Iterator<Integer> interator = listWithoutInitialCapacity100.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }


    //SIZE 1000

    @Benchmark
    public long sumArray1000_loop() {
        long sum = 0;
        for (int i = 0; i < array1000.length; i++) {
            sum += array1000[i];
        }
        return sum;
    }

    @Benchmark
    public long sumArray1000_enhancedLoop() {
        long sum = 0;
        for (int num : array1000) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public long sumList1000_loop() {
        long sum = 0;
        for (int i = 0; i < list1000.size(); i++) {
            sum += list1000.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList1000_enhancedLoop() {
        long sum = 0;
        for (Integer integer : list1000) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList1000_iterator() {
        long sum = 0;

        Iterator<Integer> interator = list1000.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    @Benchmark
    public long sumList1000WithoutInitialCapacity_loop() {
        long sum = 0;
        for (int i = 0; i < listWithoutInitialCapacity1000.size(); i++) {
            sum += listWithoutInitialCapacity1000.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList1000WithoutInitialCapacity_enhancedLoop() {
        long sum = 0;
        for (Integer integer : listWithoutInitialCapacity1000) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList1000WithoutInitialCapacity_iterator() {
        long sum = 0;

        Iterator<Integer> interator = listWithoutInitialCapacity1000.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    //SIZE 10000

    @Benchmark
    public long sumArray10000_loop() {
        long sum = 0;
        for (int i = 0; i < array10000.length; i++) {
            sum += array10000[i];
        }
        return sum;
    }

    @Benchmark
    public long sumArray10000_enhancedLoop() {
        long sum = 0;
        for (int num : array10000) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public long sumList10000_loop() {
        long sum = 0;
        for (int i = 0; i < list10000.size(); i++) {
            sum += list10000.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList10000_enhancedLoop() {
        long sum = 0;
        for (Integer integer : list10000) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList10000_iterator() {
        long sum = 0;

        Iterator<Integer> interator = list10000.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    @Benchmark
    public long sumList10000WithoutInitialCapacity_loop() {
        long sum = 0;
        for (int i = 0; i < listWithoutInitialCapacity10000.size(); i++) {
            sum += listWithoutInitialCapacity10000.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList10000WithoutInitialCapacity_enhancedLoop() {
        long sum = 0;
        for (Integer integer : listWithoutInitialCapacity10000) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList10000WithoutInitialCapacity_iterator() {
        long sum = 0;

        Iterator<Integer> interator = listWithoutInitialCapacity10000.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }


    //SIZE 100000

    @Benchmark
    public long sumArray100000_loop() {
        long sum = 0;
        for (int i = 0; i < array100000.length; i++) {
            sum += array100000[i];
        }
        return sum;
    }

    @Benchmark
    public long sumArray100000_enhancedLoop() {
        long sum = 0;
        for (int num : array100000) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public long sumList100000_loop() {
        long sum = 0;
        for (int i = 0; i < list100000.size(); i++) {
            sum += list100000.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList100000_enhancedLoop() {
        long sum = 0;
        for (Integer integer : list100000) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList100000_iterator() {
        long sum = 0;

        Iterator<Integer> interator = list100000.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }

    @Benchmark
    public long sumList100000WithoutInitialCapacity_loop() {
        long sum = 0;
        for (int i = 0; i < listWithoutInitialCapacity100000.size(); i++) {
            sum += listWithoutInitialCapacity100000.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumList100000WithoutInitialCapacity_enhancedLoop() {
        long sum = 0;
        for (Integer integer : listWithoutInitialCapacity100000) {
            sum += integer;
        }
        return sum;
    }

    @Benchmark
    public long sumList100000WithoutInitialCapacity_iterator() {
        long sum = 0;

        Iterator<Integer> interator = listWithoutInitialCapacity100000.iterator();
        while (interator.hasNext()) {
            sum += interator.next();
        }
        return sum;
    }


}
