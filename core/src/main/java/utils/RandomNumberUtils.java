package utils;

public class RandomNumberUtils {

    private RandomNumberUtils() {
    }

    public static Integer getRandomInteger(int lowerBound, int upperBound) {
        return (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }
}
