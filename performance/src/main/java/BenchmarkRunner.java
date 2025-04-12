import example.ArrayAndListCreationTests;
import example.EnumAccessTests;
import example.EnumValueOfBetweenDifferentSIzedEnumTests;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
//                .include("." + FinalExample.class.getSimpleName() + ".*")
//                .include("." + ArrayVsArrayListSumTest.class.getSimpleName() + ".*")
//                .include("." + ArrayAndListCreationTests.class.getSimpleName() + ".*")
//                .include("." + EnumAccessTests.class.getSimpleName() + ".*")
                .include("." + EnumValueOfBetweenDifferentSIzedEnumTests.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
