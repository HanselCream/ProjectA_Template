package pages;

import java.util.Random;

public class RandomTextGenerator {
    private static final String[] RANDOM_TEXTS = {
            "1NextGen Systems", "2NextGen Solutions", "3Omega Innovations Address",
            "4Davao City", "92345678", "support@nextgensystems.com",
            "8www.nextgensite.com", "Davao", "888-8888",
            "10", "Makati City", "Enterprises", "8765432101",
            "EnterprisesManila", "manilatechhub", "76543-210", "davaotechconnect@yahoo.com",
            "Manila Davao", "James Anderson", "Robert Wilson", "25TechDirector", "98765-210", "jamesa@test.com",
            "Davao", "Anna Martinez", "Sophia Roberts", "IT Solutions"
    };


    public static String getTextByIndex(int index) {
        if (index < RANDOM_TEXTS.length) {
            return RANDOM_TEXTS[index]; // Get text sequentially
        }
        return "DefaultText"; // Fallback if index is out of range
    }
}


//private static final String[] RANDOM_TEXTS = {
//        "1InnoTech Systems", "2InnoTech Solutions", "3Alpha Innovations Address",
//        "4Cebu City", "91239876", "contact@innotech.com",
//        "8www.innotechwebsite.com", "Cebu", "999-9999",
//        "10", "Quezon City", "Technologies", "9876543210",
//        "TechnologiesDavao", "davaotechhub", "65432-321", "cebuinnovate@yahoo.com",
//        "Davao Cebu", "Michael Johnson", "David Carter", "25TechManager", "87654-321", "michaelj@test.com",
//        "Cebu", "Sarah Lee", "Emily Watson", "Engineering"
//};

