package org.example;

public class Expense extends Transaction{
    //Den här klasser ärver Transaction klass, så kan denna klass använda vad som finns i Transaction klass.

    private EExpenseCategory category;// eftersom jag vill koppla min enum categories with this klass, skrev jag den


    // den här constructorn har skapats för att skapa instanser in an arrayList i min main
    public Expense(String date, String name, double amount, EExpenseCategory category ) {
        super(date, name, amount); // kommer från transaction klass med nyckelordet "super"
        this.category= category; // kommer från denna klass med nyckelordet "this"
    }

    public Expense(){ /// default constructor
        super();
    }

    // Getters och setters för att komma åt private värden - sätta värdet - ändra värdet
    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }

    //toString metod for reading outputs correctly
    @Override
    public String toString() {
        return "Expense{" +
                "category=" + category +
                "} " + super.toString();
    }
}
