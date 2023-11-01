package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BudgetTracker {
    public static void main(String[] args)  throws IOException { // Programmet kastar IOException för att skriva i JSON format

        Scanner scanner= new Scanner(System.in);
        Gson gson= new Gson(); // en instans av Gson

        String fileName="src/main/user.json"; // file path
        HashMap<Integer, User> userMap= new HashMap<>(); // hashMap for användaren list
        User user1 = new User("Fazilet", "Senol", 1990);
        User user2= new User("Hakan", "Senol", 1987);
        User user3= new User("Yigit ", "Senol", 2018);
        User user4= new User("Mert", "Senol", 2019);

        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(user3.getId(), user3);
        userMap.put(user4.getId(), user4);

        try(FileWriter fw= new FileWriter(fileName)) { // sätter file path här inne i FileWriter- fileName
            gson.toJson(userMap, fw);  // för att skriva i Json format
            fw.close();
        }
        catch (IOException e){
           e.printStackTrace();
        }

        String fileName1="src/main/income.json";
       ArrayList<Income> incomeList= new ArrayList<>();

        incomeList.add(new Income("27 january 2023", "Salary",  28000.0, EIncomeCategory.SALARY));
        incomeList.add(new Income("25 february 2023", "CSN", 13000.0, EIncomeCategory.CSN));
        incomeList.add(new Income("20 july 2023","Child Support", 2650.0, EIncomeCategory.CHILD_SUPPORT));
        incomeList.add(new Income("12 May 2023", " Second Hand Selling", 500.0, EIncomeCategory.OTHER));
        try(FileWriter fw1= new FileWriter(fileName1)) {
            gson.toJson(incomeList, fw1);
            fw1.close();
        }
        catch (IOException e){
         e.printStackTrace();
        }

        String fileName2="src/main/expense.json";
        ArrayList<Expense> expenseList= new ArrayList<>();
        expenseList.add(new Expense("25 May 2023", "House rent", 7177.0, EExpenseCategory.HOUSE_RENT));
        expenseList.add(new Expense("20 april 2023", "2 cars expenses", 10000.0, EExpenseCategory.CARS));
        expenseList.add(new Expense("30 june 2023", "Bills", 5000.0, EExpenseCategory.BILLS));
        expenseList.add(new Expense("15 december 2023", "Children", 2000.0, EExpenseCategory.CHILDREN));
        expenseList.add(new Expense("05 august 2023", "Food", 5000.0, EExpenseCategory.FOOD));

        try(FileWriter fw2= new FileWriter(fileName2)) {
            gson.toJson(expenseList, fw2);
            fw2.close();
        }
        catch (IOException e){
          e.printStackTrace();
        }

        //appen börjar här
        boolean isRunning= true;
        while(isRunning) {
            System.out.println("Welcome to the Budget Tracker program!");

            try {
                System.out.println("Please enter your id!");
                int id = scanner.nextInt();

                if (userMap.containsKey(id)) { // Om user id finns i listan, fortsätter programmet.
                    // Annars går det till else blocken och säger "Access denied". Programmet vill igen en user id.
                    User user = userMap.get(id);
                    System.out.println("Dear " +  user.getFirstname() + "! " + "Welcome to your budget tracker");
                    System.out.println("Please write a number what you want to do\n" +
                            "[1] about your income\n" +
                            "[2] about your expense\n" +
                            "[3] to see your current budget" );

                    int userChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (userChoice == 1) { // här finns 7 olika alternativ för inkomster
                        System.out.println("Please write \n" +
                                "[1] to see your total income\n" +
                                "[2] to see your fixed income category list\n" +
                                "[3] to see your incomes with amounts and dates\n" +
                                "[4] to add a new income\n" +
                                "[5] to remove an income from the list\n" +
                                "[6] to update an income category\n" +
                                "[7] to search a special income");

                        int incomeChoice = scanner.nextInt();

                        switch (incomeChoice) {

                            case 1:
                                Income income= new Income();
                                int incometotal=income.incomeTotal(incomeList);
                                // incomeTotal metod finns i Transaction klassen.
                                // Men vi kan använda också den här metoden i income klassen och med income klassens instans
                                //eftersom income classen ärver Transaction klass. Det är polyformism
                                System.out.println(" Your monthly fixed incomes are " + incometotal + " kronor");
                                break;

                            case 2:
                                IStorage incomeIStorage= new IncomeIStorageImpl(incomeList);
                                // Jag kan skapa en instans av IncomeStorageImpl med "IStorage interface" eller direkt namn av "IncomeStorageImpl".
                                //Här skrev jag interface namn. Det blir polyformism.

                                System.out.println("These are your fixed incomes categories : ");
                                incomeIStorage.readCategory();
                                break;

                            case 3:
                                Gson gson1= new Gson(); // en instans av Gson
                                Income[] incomeListJson= null; // en tom låda för att sätta in värden inne i
                                FileReader fr=new FileReader("src/main/income.json"); // file path to read from json
                                incomeListJson=gson.fromJson(fr,Income[].class); // att sätta värden i lådan
                                System.out.println("These are your incomes with amounts and dates: " );
                                for (Income incomeJson : incomeListJson){
                                    System.out.println(" Category : " + incomeJson.getCategory()+  " -  Name : " + incomeJson.getName() + " - Amount : " + incomeJson.getAmount()+ " - Date  " + incomeJson.getDate());
                                }
                                // I JSON format kan vi see BARA listan som jag har skapat i början.
                                 // Man kan inte se nya kategorins information här som användaren lägger till i listan med addNewcategory metod.
                                // Jag tror att det behövs att skriva och läsa in Json format för den nya.
                                //Men jag gjorde inte den. Annars nya informationer läggs i incomeList (in debug mod kan sees dem)
                                break;

                            case 4:
                                   IncomeIStorageImpl incomeIStorage1= new IncomeIStorageImpl(incomeList);
                                // Jag kan skapa en instans av IncomeStorageImpl med "IStorage interface" eller direkt namn av "IncomeStorageImpl".
                                //Det gör olika klasser visa olika beteenden inom samma interface(gränssnitt)
                                // Här skrev jag direkt klassens namn(sub class)
                                   var newIncome = incomeIStorage1.addNewCategory();
                                   incomeList.add(newIncome);
                                System.out.println("You added this category:  " + newIncome.getName().toUpperCase() + " with amount : " + newIncome.getAmount() + " in date : " + newIncome.getDate());
                                break;

                            case 5:
                                IStorage incomeIStorage2= new IncomeIStorageImpl(incomeList); // skapar en instans
                                incomeIStorage2.removeCategory(); // anropar metoden från IncomeStorageImpl klassen
                                break;

                            case 6:
                                IncomeIStorageImpl incomeIStorage3= new IncomeIStorageImpl(incomeList);
                                incomeIStorage3.updateCategory();
                                break;

                            case 7:
                                IncomeIStorageImpl incomeIStorage4= new IncomeIStorageImpl(incomeList);
                                incomeIStorage4.searchCategory();
                                break;

                            default:
                                System.out.println(" Please write a number between 1-7 to do something about your income!");
                        }
                    }

                    else if (userChoice == 2) {// 7 olika val för utgifter
                        System.out.println("Please write \n" +
                                "[1] to see your total expense\n" +
                                "[2] to see your fixed expense category list\n" +
                                "[3] to see your expenses with amounts and dates\n"+
                                "[4] to add a new expense \n" +
                                "[5] to remove an expense from the list\n" +
                                "[6] to update an expense \n" +
                                "[7] to search a special expense");
                        int expenseChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (expenseChoice ) {
                            case 1:
                                Expense expense= new Expense();
                                int expensetotal=expense.expenseTotal(expenseList);
                                System.out.println(" Your monthly fixed expenses are " + expensetotal + " kronor");
                                break;

                            case 2:
                                IStorage expenseIStorage= new ExpenseIStorageImpl(expenseList);
                                System.out.println("These are your fixed expenses categories : ");
                                expenseIStorage.readCategory();
                                break;

                            case 3:
                                Gson gson1= new Gson();
                                Expense[] expenseListJson= null;
                                FileReader fr=new FileReader("src/main/expense.json");
                                expenseListJson=gson.fromJson(fr,Expense[].class);
                                System.out.println("These are your expenses with amounts and dates: " );
                                for (Expense expenseJson : expenseListJson){
                                    System.out.println(" Category : " + expenseJson.getCategory()+  " -  Name : " + expenseJson.getName() + " - Amount : " + expenseJson.getAmount()+ " - Date  " + expenseJson.getDate());
                                }
                                break;

                            case 4:
                                ExpenseIStorageImpl expenseIStorage2= new ExpenseIStorageImpl(expenseList);
                                var newExpense=expenseIStorage2.addNewCategory();
                                expenseList.add(newExpense);
                                System.out.println("You added this category:  " + newExpense.getDate().toUpperCase() + " with amount :" + newExpense.getAmount() + " in date :" + newExpense.getName());
                                break;

                            case 5:
                                ExpenseIStorageImpl expenseIStorage1= new ExpenseIStorageImpl(expenseList);
                                expenseIStorage1.removeCategory();
                                break;

                            case 6:
                                ExpenseIStorageImpl expenseIStorage3= new ExpenseIStorageImpl(expenseList);
                                expenseIStorage3.updateCategory();
                                break;

                            case 7:
                                ExpenseIStorageImpl expenseIStorage4= new ExpenseIStorageImpl(expenseList);
                                expenseIStorage4.searchCategory();
                                break;

                            default:
                                System.out.println(" Please write a number between 1-7 to do something about your expense!");
                        }
                    }
                    else if (userChoice == 3) {
                              Transaction transaction= new Transaction();
                              int monthlyResult= transaction.calculateMonthly(incomeList,expenseList);
                        System.out.println("Your current budget is : " + monthlyResult + "  kronor");
                    }

                    else {
                        System.out.println("Invalid number! Please write a number between 1-3!");
                        // userChoise control
                    }
                }
                else {
                    System.out.println(" Access denied! Please write your id correctly!");
                    //users Id control
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid ID.");
                scanner.next(); // när user skriver bokstav osv, säger programmet att skriva id.
            } catch (Exception e) {
                System.out.println("Invalid character! Please try again!" + e.getMessage() + e.toString());
            } finally {
                System.out.println("The program has ended! Thank you for using this program! ");
                //Denna blocken kör alltid.
            }
        }
    }
}
