package org.example;

public enum EExpenseCategory {
    OTHER, HOUSE_RENT, CARS, CHILDREN, FOOD, BILLS;

    public static boolean contains(String newCategoryName) {
        for (EExpenseCategory category : values()) {
            if (category.name().equalsIgnoreCase(newCategoryName)) {
                return true;
            }
        }
        return false;

    }
}