package org.example;

import java.util.*;

public class IncomeIStorageImpl implements IStorage {
    //Den här klassen implementerar IStorage interface för att använda metoder med overriding
    // jag döpte den med IncomeStorage- impl- för att visa den här klassen implementerar en interface
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Income> incomeList;

    public IncomeIStorageImpl(ArrayList<Income> incomeList) {
        this.incomeList = incomeList;
    }

    @Override
    public Income addNewCategory() {// en metod för att lägga till en ny income
        System.out.println("Please write the name of category which you want to add to your income list");
        String newIncomeName = scanner.next().toUpperCase();
        scanner.nextLine(); //en tom enter för att inte fortsätta programmet innan det tar input från användaren

        if (!EIncomeCategory.contains(newIncomeName)) { // om det inte finns kategori som användaren vill lägga till, kan man läggas en ny.
            System.out.println("Please write the date");
            String newCategoryDate;
            newCategoryDate = scanner.nextLine();
            System.out.println("Please write the amount ");
            int newCategoryAmount;
            try {
                newCategoryAmount = scanner.nextInt();

                return new Income(newCategoryDate, newIncomeName, newCategoryAmount, EIncomeCategory.OTHER);
            } catch (InputMismatchException e) { // om användaren skriver bokstav istället för nummer för belopp
                System.out.println("Invalid input. Please enter numbers for the amount.");
                return null;
            }
        } else { // om värdet som användaren skriver för att lägga, säger programmet att denna kategorin finns.
            System.out.println("This category already exists!");
            return null;
        }
    }

    @Override
    public void removeCategory() {
        System.out.println("Please write the name of category which you want to remove from your income list");
        String categoryName = scanner.next().toUpperCase();

        if (EIncomeCategory.contains(categoryName)) {
            incomeList.remove(categoryName);
            System.out.println("You deleted this category:  " + categoryName.toUpperCase() + " from your income list successfully");

        } else {
            System.out.println("This category was not found!");
        }
    }

    @Override
    public Income updateCategory() { //här använder jag setters för att uppdatera incomes
        System.out.println("Please write the name of category that you want to update");
        String oldIncomeName = scanner.next().toUpperCase();
        boolean categoryFound = false;


        for (Income income : incomeList) {
            if (income.getName().equalsIgnoreCase(oldIncomeName)) {

                System.out.println("Please write the updated name : ");
                String updatedName = scanner.next().toUpperCase();
                System.out.println("Please write the updated date : ");
                String updatedDate = scanner.next().toUpperCase();
                System.out.println("Please write the updated amount : ");
                int updatedAmount;
                try {
                    scanner.nextLine();
                    updatedAmount = scanner.nextInt();

                    income.setName(updatedName);
                    income.setDate(updatedDate);
                    income.setAmount(updatedAmount);

                    System.out.println(" You updated " + oldIncomeName + " with new name " + updatedName + " amount : " + updatedAmount + " date : " + updatedDate);
                    incomeList.add(new Income(updatedDate, updatedName, updatedAmount, EIncomeCategory.OTHER));
                    categoryFound = true;
                    return new Income(updatedDate, updatedName, updatedAmount, EIncomeCategory.OTHER);

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers for the amount.");
                    return null;
                }
            }
            } if (!categoryFound) {
                System.out.println("This category is not exists!");
            }
        return null;
        }
    @Override
    public void readCategory() {
        for (EIncomeCategory incomeCategory : EIncomeCategory.values()) {
            System.out.println(incomeCategory);
        }

    }

    @Override
    public void searchCategory() {
        System.out.println("Please write the name of income category that you want to see");
        String searchIncomeCategory= scanner.nextLine();
        if (EIncomeCategory.contains(searchIncomeCategory)) {
            Income income = new Income();
            for (Income income1 : incomeList) {
    System.out.println(" You can read it : " + income1);
    break;
               }
            }
        }

    }