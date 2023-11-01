package org.example;

public enum EIncomeCategory {

    OTHER, SALARY, CSN, CHILD_SUPPORT;

    // Jag har skrivit en metod här för att kontrollera om värdet som använderen skriver, finns i vår category.
    // Jag använder den här metoden i några metoder(addnewCategory, updateCategory eller removeCategory)
    public static boolean contains(String newCategoryName) {
        for (EIncomeCategory category : values()) {
            if (category.name().equalsIgnoreCase(newCategoryName)) {
                return true;
            }
        }
        return false;

    }
}
