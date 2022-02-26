package operator.examples;

public class StaticOperatorExamples {


    public static void prefixIncrementExample() {
        int x = 5;
        System.out.println("\nprefixIncrementExample: \nSet up x = 5, and print --x right after.\nResult is: " + --x);
        System.out.println("\nWe can see that the prefix is calculated BEFORE the sout line is.\n");
    }

    public static void postfixIncrementExample() {
        int x = 5;
        System.out.println("\npostfixIncrementExample: \nSet up x = 5, and print x-- right after.\nResult is: " + x--);
        System.out.println("\nWe can see that the postfix decrement is calculated AFTER the sout line is.\n");

    }

    public static void shortMultiplicationExample() {
        short x = 10, y = 2;
//        short z = x * y; // This does not compile, since multiplication of short is always an int
        short z = (short) (x * y);

        System.out.println("\nMultiplying(or any arithmetic operation) 2 shorts results in an integer, so in order to have short z = x * y [Where x,y are both short] i need to cast the result. " +
                "Otherwise it does not compile!!!Same happens with byte type!\n Both short and byte get promoted to int before arithmetic operations\n");
    }


    public static void example1() {
        int x = 5;
        double y = 2 + 2 * x--;
        System.out.println("\nExample 1 result is: " + y);
    }

    /**
     * Infinite Loop example. It will compile and run normally but the loop will never end
     */
    public static void example2() {
        int a = 1, b = 2;
        while (a < 3) {
            b++;
            System.out.println("a= " + a + ",b= " + b);
        }
    }

}
