package operator.examples;

public class Main {


    public static void main(String[] args) {

        System.out.println("Main examples:\n");
        StaticOperatorExamples.prefixIncrementExample();
        StaticOperatorExamples.postfixIncrementExample();
        StaticOperatorExamples.shortMultiplicationExample();

        System.out.println("Secondary examples:\n");
        StaticOperatorExamples.example1();
//        StaticOperatorExamples.example2(); // Infinite loop

    }
}
