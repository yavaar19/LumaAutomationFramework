package Utilities;

import com.github.javafaker.Faker;


public class StringGenerator {

    private static Faker faker = new Faker();

    public static String getFirstName() {

        return faker.name().firstName();

    }

    public static String getLastName() {

        return faker.name().lastName();

    }

    public static String getEmail() {

        return faker.internet().emailAddress();

    }

    public static String getPassword() {

        return faker.internet().password(9, 12, true, true).replace("\\", "T").concat("Hz");

    }

    public static String getModifiedEmail(String character, String location, int times) {

        String email = getEmail();

        StringBuilder stringBuilder = new StringBuilder();

        String shortEmail = email.split("@")[0];
        String domain = email.split("@")[1];

        String firstHalf = shortEmail.substring(0, shortEmail.length()/2);
        String secondHalf = shortEmail.substring(shortEmail.length()/2);

        if (location.equals("leading")) stringBuilder.append(character.repeat(times)).append(firstHalf).append(secondHalf).append("@").append(domain);
        if (location.equals("middle")) stringBuilder.append(firstHalf).append(character.repeat(times)).append(secondHalf).append("@").append(domain);
        if (location.equals("trailing")) stringBuilder.append(firstHalf).append(secondHalf).append(character.repeat(times)).append("@").append(domain);

        return stringBuilder.toString();

    }

}
