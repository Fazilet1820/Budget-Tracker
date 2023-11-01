package org.example;

import java.util.ArrayList;

public class Transaction {
    // I den här klassen skriver jag tre private fälter
    // 3 metoder
    private String date;
    private String name;
    private double amount;

    public Transaction(String date, String name, double amount) { // aoutogenererat konstruktorn
        this.date = date;
        this.name = name;
        this.amount = amount;
    }

    public Transaction() {
    }

    public int incomeTotal(ArrayList<Income> incomeList){ // en metod för att räkna alla inkomst i arrayList for incomes
        int incomeTotal=0;
        for (Income income: incomeList) {
            incomeTotal+=income.getAmount();
        }
        return incomeTotal;
    }

    public int expenseTotal(ArrayList<Expense> expenseList) { // en metod för att räkna alla utgifter i arrayList for expenses
        int expenseTotal=0;
        for(Expense expense: expenseList){
            expenseTotal+=expense.getAmount();
        }
        return expenseTotal;
    }


    public int calculateMonthly(ArrayList<Income> incomeList,ArrayList<Expense> expenseList ){
        //en metot för att räkna current budget
        int incomeTotal= incomeTotal(incomeList);
        int expenseTotal=expenseTotal(expenseList);
        return incomeTotal-expenseTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
