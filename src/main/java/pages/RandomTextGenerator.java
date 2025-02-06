package pages;

import java.util.Random;

public class RandomTextGenerator {
    private static final String[] RANDOM_TEXTS = {
                    "1TechNova Systems", "2TechNova Solutions", "3Beta Innovations Address",
                    "4Laguna City", "91234567", "info@technova.com",
                    "8www.technovawebsite.com", "Laguna", "777-7777",
                    "10", "Manila", "Systems", "8765432109",
                    "SystemsDavao", "davaosystems", "54321-123", "davaotech@yahoo.com",
                    "Davao Laguna", "John Doe", "John Smith", "25TechLead", "98765-432", "johndoe@test.com",
                    "Laguna", "Jane Doe", "Jane Party", "Development"
            };

    public static String getTextByIndex(int index) {
        if (index < RANDOM_TEXTS.length) {
            return RANDOM_TEXTS[index]; // Get text sequentially
        }
        return "DefaultText"; // Fallback if index is out of range
    }
}
