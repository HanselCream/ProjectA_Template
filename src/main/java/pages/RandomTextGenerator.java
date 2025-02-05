package pages;

import java.util.Random;

public class RandomTextGenerator {
    private static final String[] RANDOM_TEXTS = {
            "1Radam Technologies", "2Radam Solutions", "3Alpha Innovations Address",
            "4Rosario Cavite", "5Luzon Philippines", "641176",
            "7792677851", "8www.hanswebsitetest.com", "9L9OCAL@GMAIL.COM",
            "Filipino", "Makati", "Cebu", "D13avao",
            "1414110", "1ax12344", "16", "17",
            "18", "HansTEST", "HansSurname", "21TitleOfHans", "RadamTechnologies"
    };

    public static String getTextByIndex(int index) {
        if (index < RANDOM_TEXTS.length) {
            return RANDOM_TEXTS[index]; // Get text sequentially
        }
        return "DefaultText"; // Fallback if index is out of range
    }
}
