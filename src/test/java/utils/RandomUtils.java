package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }

        return result.toString();
    }

    public static Integer getRandomNumber(Integer min, Integer max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static String getRandomEmail() {
        return getRandomString(10) + "@qa.guru";
    }

    public static String getRandomMonth() {
        String[] listOfMonth = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };
        int MonthIndex = getRandomNumber(0, 11);
        return listOfMonth[MonthIndex];
    }
    public static String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        int hobbyIndex = getRandomNumber(0, 2);
        return hobbies[hobbyIndex];
    }
    public static String getRandomDay() {
        int day = getRandomNumber(1, 30);
        return String.valueOf(day);
    }

    public static String getRandomYear() {
        int year = getRandomNumber(1999, 2034);
        return String.valueOf(year);
    }
}
