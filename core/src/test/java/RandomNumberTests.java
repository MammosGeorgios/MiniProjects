import org.junit.jupiter.api.Test;
import utils.RandomNumberUtils;


class RandomNumberTests {


    @Test
    void testRandomNumberWithin1And10() {
        Integer randomNumber = RandomNumberUtils.getRandomInteger(1, 10);
        System.out.println("Random Number Generated is: " + randomNumber);
        assert (randomNumber >= 1 && randomNumber <= 10);
    }

    @Test
    void testRandomNumberBetweenSameValues(){

        Integer randomNumber = RandomNumberUtils.getRandomInteger(10, 10);
        System.out.println("Random Number Generated is: " + randomNumber);
        assert (randomNumber == 10);
    }
}
