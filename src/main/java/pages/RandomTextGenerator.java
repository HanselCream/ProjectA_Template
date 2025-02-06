package pages;

import java.util.Random;

public class RandomTextGenerator {
    private static final String[] RANDOM_TEXTS = {
            "1InnoTech Systems", "2InnoTech Solutions", "3Alpha Innovations Address",
            "4Cebu City", "91239876", "contact@innotech.com",
            "8www.innotechwebsite.com", "Cebu", "999-9999",
            "10", "Quezon City", "Technologies", "9876543210",
            "TechnologiesDavao", "davaotechhub", "65432-321", "cebuinnovate@yahoo.com",
            "Davao Cebu", "Michael Johnson", "David Carter", "25TechManager", "87654-321", "michaelj@test.com",
            "Cebu", "Sarah Lee", "Emily Watson", "Engineering"
    };


    public static String getTextByIndex(int index) {
        if (index < RANDOM_TEXTS.length) {
            return RANDOM_TEXTS[index]; // Get text sequentially
        }
        return "DefaultText"; // Fallback if index is out of range
    }
}
