package org.example;

public interface IStorage {
    //Eftersom det är en interface, döpte jag med bokstav -I-Storage
    // In this interface finns signaturen för metoderna för klassen IncomeStorageImpl och ExpenseStorageImpl som implementerar denna klass.
    // Jag vill skriva dem eftersom både expenseStorage och incomeStorage har samma metoder.
    //Det gör att programmet ska vara snabbare och det gör också att en typ kan bete sig som några typer när vi skapar en instans av klassen(Jag visar den i vår main klass-budgetTracker)
   public Transaction addNewCategory();
    public void removeCategory();
    public Transaction updateCategory();
    public void readCategory();
    public void searchCategory();







}

