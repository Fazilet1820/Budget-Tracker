package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenseIStorageImpl implements IStorage {
    // jag döpte den med ExpenseStorage- impl- för att visa den här klassen implementerar en interface
    Scanner scanner= new Scanner(System.in);
    private ArrayList<Expense> expenseList;
    public ExpenseIStorageImpl(ArrayList<Expense> expenseList){// maindeki listeyle baglanti kurabilmek icin bunu ekledik
        this.expenseList= expenseList;
    }


    //De här metodernas logik är samma som IncomeStorageImpl, så skriver jag inte igen deras förklaringar.
    @Override
    public Expense addNewCategory() {
        System.out.println("Please write the name of category which you want to add to your expense list");
        String newCategoryName = scanner.next().toUpperCase();
        scanner.nextLine();

        if (!EExpenseCategory.contains(newCategoryName)) {
            System.out.println("Please write the date");
            String newCategoryDate;
            newCategoryDate = scanner.nextLine();
            System.out.println("Please write the amount ");
            int newCategoryAmount;
           try { newCategoryAmount = scanner.nextInt();
            scanner.nextLine();
            //System.out.println("You added this category:  " + newCategoryName.toUpperCase() + " to your expense list with amount " + newCategoryAmount + " successfully " );
            return new Expense(newCategoryName, newCategoryDate, newCategoryAmount, EExpenseCategory.OTHER);
        } catch (InputMismatchException e) {
               System.out.println("Invalid input. Please enter numbers for the amount.");
               return null;
           }
        }
        else{
            System.out.println("This category already exists!");
            return null;
        }

    }

    @Override
    public void removeCategory() {
        System.out.println("Please write the name of category which you want to remove from your expense list");
        String categoryName = scanner.next().toUpperCase();

        if (EExpenseCategory.contains(categoryName)) {
            expenseList.remove(categoryName);
            System.out.println("You deleted this category:  " + categoryName.toUpperCase() + " from your expense list successfully" );

        }
        else {
            System.out.println("This category was not found!");

        }
    }

    @Override
    public Expense updateCategory() {

        System.out.println("Please write the name of category that you want to update");
        String oldExpenseName = scanner.next().toUpperCase();
        boolean categoryFound= false;

        for (Expense expense: expenseList) {
    if (expense.getName().equalsIgnoreCase(oldExpenseName)) {

        System.out.println("Please write the updated name : ");
        String updatedName = scanner.next().toUpperCase();
        System.out.println("Please write the updated date : ");
        String updatedDate = scanner.next().toUpperCase();
        System.out.println("Please write the updated amount : ");
        int updatedAmount;

        try {
            scanner.nextLine();
            updatedAmount = scanner.nextInt();
            expense.setName(updatedName);
            expense.setDate(updatedDate);
            expense.setAmount(updatedAmount);

            System.out.println(" You updated " + oldExpenseName + " with new name " + updatedName + " amount : " + updatedAmount + " date : " + updatedDate);
            expenseList.add(new Expense(updatedDate, updatedName, updatedAmount, EExpenseCategory.OTHER));
            categoryFound=true;
            return new Expense(updatedDate, updatedName, updatedAmount, EExpenseCategory.OTHER);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for the amount.");
            return null;
        }
    }
        }
        if (!categoryFound) {
        System.out.println("This category is not exists!");
    }
        return null;
    }

    @Override
    public void readCategory() {
        for (EExpenseCategory expenseCategory: EExpenseCategory.values()){
            System.out.println(expenseCategory);
        }
    }



    @Override
    public void searchCategory() {
        System.out.println("Please write the name of expense category that you want to see");
        String searchExpenseCategory = scanner.nextLine();
        if (EExpenseCategory.contains(searchExpenseCategory)) {
            Expense expense = new Expense();
            for (Expense expense1 : expenseList) {
                System.out.println(" You can read it : " + expense1);
                break;

            }
        }
    }
}