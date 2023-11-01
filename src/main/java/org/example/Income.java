package org.example;

public class Income extends Transaction{
    //Den här klasser ärver Transaction klass, så kan denna klass använda vad som finns i Transcation klass.

    private EIncomeCategory category; // eftersom jag vill koppla min enum categories with this klass, skrev jag den


    public Income(String date, String name, double amount, EIncomeCategory category ) {
        // den här constructorn har skapats för att skapa instanser in an arrayList i min main

        super(date, name, amount); // kommer från transaction klass med nyckelordet "super"
        this.category= category; // den kommer från denna klass med nyckelordet "this"
    }
    public Income(){ // default constructor
        super();
    }

    // Getters och setters för att komma åt private värden - sätta värdet - ändra värdet
    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }


    //toString metod for reading outputs correctly
    @Override
    public String toString() {
        return "Income{" +
                "category=" + category +
                "} " + super.toString();
    }
}
