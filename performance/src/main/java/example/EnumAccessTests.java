package example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class EnumAccessTests {

    /*
    Test is about having an enum and needing to search for it by its string value. In a legacy app we have the bellow setup (yes, with the name duplication).
    The test is about comparing valueOf with looping over MyEnum.values().

    Some interesting points based on the results:
    - valueOf is faster for results that are not in the first enum values, but iteration will be faster for the first few values
    - valueOf method seems to have a consistent cost (perhaps a much bigger enum list would affect this - should test separately)
    - as expected, the order matters a lot if you are going to loop over items
    - having a null check at the start doesn't seem to affect performance in any significant or consistent way
    - valueOf IllegalArgumentException cost is massive compared to any other measurement. If we expect to have lookup values what cannot be matched to the enum, we should use iteration
    - finding a value based on int instead of string is faster when looping
     */

//    Benchmark                                                                               Mode  Cnt     Score    Error  Units
//    EnumAccessTests.valueA_byLoopingOfAsciiValue                                            avgt   25     0,813 ±  0,013  ns/op
//    EnumAccessTests.valueA_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt         avgt   25     0,821 ±  0,015  ns/op
//    EnumAccessTests.valueA_byLoopingOfEnumName                                              avgt   25     0,823 ±  0,006  ns/op
//    EnumAccessTests.valueA_byLoopingOfEnumNameWithoutNullCheck                              avgt   25     0,826 ±  0,008  ns/op
//    EnumAccessTests.valueA_byLoopingOfPropertyName                                          avgt   25     0,827 ±  0,009  ns/op
//    EnumAccessTests.valueA_byLoopingOfPropertyNameWithoutNullCheck                          avgt   25     0,827 ±  0,009  ns/op
//    EnumAccessTests.valueA_byValueOfMethod                                                  avgt   25     3,068 ±  0,039  ns/op
//    EnumAccessTests.valueA_byValueOfMethodWithoutNullCheck                                  avgt   25     3,050 ±  0,020  ns/op
//    EnumAccessTests.valueE_byLoopingOfAsciiValue                                            avgt   25    14,726 ±  0,259  ns/op
//    EnumAccessTests.valueE_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt         avgt   25    14,649 ±  0,117  ns/op
//    EnumAccessTests.valueE_byLoopingOfEnumName                                              avgt   25    24,547 ±  0,284  ns/op
//    EnumAccessTests.valueE_byLoopingOfEnumNameWithoutNullCheck                              avgt   25    24,718 ±  0,284  ns/op
//    EnumAccessTests.valueE_byLoopingOfPropertyName                                          avgt   25    24,714 ±  0,274  ns/op
//    EnumAccessTests.valueE_byLoopingOfPropertyNameWithoutNullCheck                          avgt   25    24,891 ±  0,262  ns/op
//    EnumAccessTests.valueE_byValueOfMethod                                                  avgt   25     3,069 ±  0,031  ns/op
//    EnumAccessTests.valueE_byValueOfMethodWithoutNullCheck                                  avgt   25     3,081 ±  0,051  ns/op
//    EnumAccessTests.valueNotFound_byLoopingOfAsciiValue                                     avgt   25    23,395 ±  0,183  ns/op
//    EnumAccessTests.valueNotFound_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt  avgt   25    23,514 ±  0,187  ns/op
//    EnumAccessTests.valueNotFound_byLoopingOfEnumName                                       avgt   25    83,253 ±  0,689  ns/op
//    EnumAccessTests.valueNotFound_byLoopingOfEnumNameWithoutNullCheck                       avgt   25    83,080 ±  1,443  ns/op
//    EnumAccessTests.valueNotFound_byLoopingOfPropertyName                                   avgt   25    83,600 ±  1,388  ns/op
//    EnumAccessTests.valueNotFound_byLoopingOfPropertyNameWithoutNullCheck                   avgt   25    82,120 ±  0,948  ns/op
//    EnumAccessTests.valueNotFound_byValueOfMethod                                           avgt   25  1608,482 ± 20,388  ns/op
//    EnumAccessTests.valueNotFound_byValueOfMethodWithoutNullCheck                           avgt   25  1603,697 ± 18,661  ns/op
//    EnumAccessTests.valueX_byLoopingOfAsciiValue                                            avgt   25    22,772 ±  0,211  ns/op
//    EnumAccessTests.valueX_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt         avgt   25    22,716 ±  0,174  ns/op
//    EnumAccessTests.valueX_byLoopingOfEnumName                                              avgt   25    76,616 ±  0,703  ns/op
//    EnumAccessTests.valueX_byLoopingOfEnumNameWithoutNullCheck                              avgt   25    81,631 ±  6,448  ns/op
//    EnumAccessTests.valueX_byLoopingOfPropertyName                                          avgt   25    77,632 ±  0,753  ns/op
//    EnumAccessTests.valueX_byLoopingOfPropertyNameWithoutNullCheck                          avgt   25    78,093 ±  0,546  ns/op
//    EnumAccessTests.valueX_byValueOfMethod                                                  avgt   25     3,054 ±  0,021  ns/op
//    EnumAccessTests.valueX_byValueOfMethodWithoutNullCheck                                  avgt   25     2,980 ±  0,087  ns/op

    private enum MyEnum {

        VALUE_A("VALUE_A", "This is a description for VALUE_A", "A", 65),
        VALUE_B("VALUE_B", "This is a description for VALUE_B", "B", 66),
        VALUE_C("VALUE_C", "This is a description for VALUE_C", "C", 67),
        VALUE_D("VALUE_D", "This is a description for VALUE_D", "D", 68),
        VALUE_E("VALUE_E", "This is a description for VALUE_E", "E", 69),
        VALUE_F("VALUE_F", "This is a description for VALUE_F", "F", 70),
        VALUE_G("VALUE_G", "This is a description for VALUE_G", "G", 71),
        VALUE_H("VALUE_H", "This is a description for VALUE_H", "H", 72),
        VALUE_I("VALUE_I", "This is a description for VALUE_I", "I", 73),
        VALUE_J("VALUE_J", "This is a description for VALUE_J", "J", 74),
        VALUE_K("VALUE_K", "This is a description for VALUE_K", "K", 75),
        VALUE_L("VALUE_L", "This is a description for VALUE_L", "L", 76),
        VALUE_M("VALUE_M", "This is a description for VALUE_M", "M", 77),
        VALUE_N("VALUE_N", "This is a description for VALUE_N", "N", 78),
        VALUE_O("VALUE_O", "This is a description for VALUE_O", "O", 79),
        VALUE_P("VALUE_P", "This is a description for VALUE_P", "P", 80),
        VALUE_Q("VALUE_Q", "This is a description for VALUE_Q", "Q", 81),
        VALUE_R("VALUE_R", "This is a description for VALUE_R", "R", 82),
        VALUE_S("VALUE_S", "This is a description for VALUE_S", "S", 83),
        VALUE_T("VALUE_T", "This is a description for VALUE_T", "T", 84),
        VALUE_U("VALUE_U", "This is a description for VALUE_U", "U", 85),
        VALUE_V("VALUE_V", "This is a description for VALUE_V", "V", 86),
        VALUE_W("VALUE_W", "This is a description for VALUE_W", "W", 87),
        VALUE_X("VALUE_X", "This is a description for VALUE_X", "X", 88),
        VALUE_Y("VALUE_Y", "This is a description for VALUE_Y", "Y", 89);
        // Z is the missing value


        MyEnum(String customName, String description, String letterValue, int ascii) {
            this.customName = customName;
            this.description = description;
            this.letterValue = letterValue;
            this.ascii = ascii;
        }

        final String customName;
        final String description;
        final String letterValue;
        final int ascii;

        static MyEnum getWithValueOf(String lookUpValue) {
            if (lookUpValue == null
                    || lookUpValue.isEmpty()) {
                return null;
            }

            try {
                return MyEnum.valueOf(lookUpValue);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        static MyEnum getWithValueOfWithoutNullCheck(String lookUpValue) {
            try {
                return MyEnum.valueOf(lookUpValue);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        static MyEnum getWithLoopingOfEnumName(String lookUpValue) {
            if (lookUpValue == null
                    || lookUpValue.isEmpty()) {
                return null;
            }

            for (MyEnum myEnum : MyEnum.values()) {
                if (myEnum.name().equals(lookUpValue)) {
                    return myEnum;
                }
            }

            return null;
        }

        static MyEnum getWithLoopingOfEnumNameWithoutNullCheck(String lookUpValue) {
            for (MyEnum myEnum : MyEnum.values()) {
                if (myEnum.name().equals(lookUpValue)) {
                    return myEnum;
                }
            }

            return null;
        }

        static MyEnum getWithLoopingOfPropertyName(String lookUpValue) {
            if (lookUpValue == null
                    || lookUpValue.isEmpty()) {
                return null;
            }

            for (MyEnum myEnum : MyEnum.values()) {
                if (myEnum.customName.equals(lookUpValue)) {
                    return myEnum;
                }
            }

            return null;
        }


        static MyEnum getWithLoopingOfPropertyNameWithoutNullCheck(String lookUpValue) {
            for (MyEnum myEnum : MyEnum.values()) {
                if (myEnum.customName.equals(lookUpValue)) {
                    return myEnum;
                }
            }

            return null;
        }

        static MyEnum getWithLoopingOfAsciiValue(Integer ascii) {
            if (ascii == null) {
                return null;
            }

            for (MyEnum myEnum : MyEnum.values()) {
                if (ascii.equals(myEnum.ascii)) {
                    return myEnum;
                }
            }

            return null;
        }

        static MyEnum getWithLoopingOfAsciiValueWithoutNullCheck(Integer ascii) {
            for (MyEnum myEnum : MyEnum.values()) {
                if (ascii.equals(myEnum.ascii)) {
                    return myEnum;
                }
            }

            return null;
        }

        static MyEnum getWithLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt(int ascii) {

            for (MyEnum myEnum : MyEnum.values()) {
                if (ascii == myEnum.ascii) {
                    return myEnum;
                }
            }

            return null;
        }


    }


    @Benchmark
    public static MyEnum valueA_byValueOfMethod() {
        return MyEnum.getWithValueOf("VALUE_A");
    }

    @Benchmark
    public static MyEnum valueE_byValueOfMethod() {
        return MyEnum.getWithValueOf("VALUE_E");
    }

    @Benchmark
    public static MyEnum valueX_byValueOfMethod() {
        return MyEnum.getWithValueOf("VALUE_X");
    }

    @Benchmark
    public static MyEnum valueNotFound_byValueOfMethod() {
        return MyEnum.getWithValueOf("VALUE_Z");
    }


    @Benchmark
    public static MyEnum valueA_byValueOfMethodWithoutNullCheck() {
        return MyEnum.getWithValueOfWithoutNullCheck("VALUE_A");
    }

    @Benchmark
    public static MyEnum valueE_byValueOfMethodWithoutNullCheck() {
        return MyEnum.getWithValueOfWithoutNullCheck("VALUE_E");
    }

    @Benchmark
    public static MyEnum valueX_byValueOfMethodWithoutNullCheck() {
        return MyEnum.getWithValueOfWithoutNullCheck("VALUE_X");
    }

    @Benchmark
    public static MyEnum valueNotFound_byValueOfMethodWithoutNullCheck() {
        return MyEnum.getWithValueOfWithoutNullCheck("VALUE_Z");
    }


    @Benchmark
    public static MyEnum valueA_byLoopingOfEnumName() {
        return MyEnum.getWithLoopingOfEnumName("VALUE_A");
    }

    @Benchmark
    public static MyEnum valueE_byLoopingOfEnumName() {
        return MyEnum.getWithLoopingOfEnumName("VALUE_E");
    }

    @Benchmark
    public static MyEnum valueX_byLoopingOfEnumName() {
        return MyEnum.getWithLoopingOfEnumName("VALUE_X");
    }

    @Benchmark
    public static MyEnum valueNotFound_byLoopingOfEnumName() {
        return MyEnum.getWithLoopingOfEnumName("VALUE_Z");
    }


    @Benchmark
    public static MyEnum valueA_byLoopingOfEnumNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfEnumNameWithoutNullCheck("VALUE_A");
    }

    @Benchmark
    public static MyEnum valueE_byLoopingOfEnumNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfEnumNameWithoutNullCheck("VALUE_E");
    }

    @Benchmark
    public static MyEnum valueX_byLoopingOfEnumNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfEnumNameWithoutNullCheck("VALUE_X");
    }

    @Benchmark
    public static MyEnum valueNotFound_byLoopingOfEnumNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfEnumNameWithoutNullCheck("VALUE_Z");
    }


    @Benchmark
    public static MyEnum valueA_byLoopingOfPropertyName() {
        return MyEnum.getWithLoopingOfPropertyName("VALUE_A");
    }

    @Benchmark
    public static MyEnum valueE_byLoopingOfPropertyName() {
        return MyEnum.getWithLoopingOfPropertyName("VALUE_E");
    }

    @Benchmark
    public static MyEnum valueX_byLoopingOfPropertyName() {
        return MyEnum.getWithLoopingOfPropertyName("VALUE_X");
    }

    @Benchmark
    public static MyEnum valueNotFound_byLoopingOfPropertyName() {
        return MyEnum.getWithLoopingOfPropertyName("VALUE_Z");
    }


    @Benchmark
    public static MyEnum valueA_byLoopingOfPropertyNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfPropertyNameWithoutNullCheck("VALUE_A");
    }

    @Benchmark
    public static MyEnum valueE_byLoopingOfPropertyNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfPropertyNameWithoutNullCheck("VALUE_E");
    }

    @Benchmark
    public static MyEnum valueX_byLoopingOfPropertyNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfPropertyNameWithoutNullCheck("VALUE_X");
    }

    @Benchmark
    public static MyEnum valueNotFound_byLoopingOfPropertyNameWithoutNullCheck() {
        return MyEnum.getWithLoopingOfPropertyNameWithoutNullCheck("VALUE_Z");
    }


    @Benchmark
    public static MyEnum valueA_byLoopingOfAsciiValue() {
        return MyEnum.getWithLoopingOfAsciiValue(65);
    }

    @Benchmark
    public static MyEnum valueE_byLoopingOfAsciiValue() {
        return MyEnum.getWithLoopingOfAsciiValue(69);
    }

    @Benchmark
    public static MyEnum valueX_byLoopingOfAsciiValue() {
        return MyEnum.getWithLoopingOfAsciiValue(88);
    }

    @Benchmark
    public static MyEnum valueNotFound_byLoopingOfAsciiValue() {
        return MyEnum.getWithLoopingOfAsciiValue(90);
    }


    @Benchmark
    public static MyEnum valueA_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt() {
        return MyEnum.getWithLoopingOfAsciiValueWithoutNullCheck(65);
    }

    @Benchmark
    public static MyEnum valueE_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt() {
        return MyEnum.getWithLoopingOfAsciiValueWithoutNullCheck(69);
    }

    @Benchmark
    public static MyEnum valueX_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt() {
        return MyEnum.getWithLoopingOfAsciiValueWithoutNullCheck(88);
    }

    @Benchmark
    public static MyEnum valueNotFound_byLoopingOfAsciiValueWithoutNullCheckAndWithPrimitiveInt() {
        return MyEnum.getWithLoopingOfAsciiValueWithoutNullCheck(90);
    }


}
