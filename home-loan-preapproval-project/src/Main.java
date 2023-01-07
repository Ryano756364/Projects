package src;

import customers.Customers;

public class Main {
    public static void main(String[] args){

        Customers customer1 = new Customers("Bob", "Malarkey", "testemail@gmail.com", 123, "Tuttle Ave",
                "Columbus", "OH", 43215, 1234567890, 740);

        System.out.println(customer1.getFullName());


    }
}
