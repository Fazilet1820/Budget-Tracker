package org.example;

public class User {
    // 3 private instansvariabler har skapats för varje användare
    private String firstname;
    private String lastName;
    private int id;


    // en constructor med 3 parametrar
    public User(String firstname, String lastName, int id) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.id = id;
    }

    // en deafult constructor
    public User() {
    }


    // getters(accessorer) för att komma åt värden på private fälter i en annan klass
    // det behövs inte setters(mutarorer) eftersom man inte sätta nyt värdet eller ändra värdet.
    public String getFirstname() {
        return firstname;
    }


    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

}
