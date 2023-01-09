package src;

import src.customers.Customers;

public class Main {
    public static void main(String[] args){

        // Starting program out with dummy data
        for(int i = 1; i < 6; i++){
            Customers customer = new Customers(00000 + i,
                    switch (i){
                        case 1 -> "John";
                        case 2 -> "Tom";
                        case 3 -> "Charles";
                        case 4 -> "Ralphie";
                        case 5 -> "Angelina";
                        default -> "Anonymous";
                    },
                    switch (i){
                        case 1 -> "Charles";
                        case 2 -> "Smith";
                        case 3 -> "Liam";
                        case 4 -> "Kid";
                        case 5 -> "Marie";
                        default -> "Anonymous";
                    },switch (i){
                        case 1 -> "3305550000";
                        case 2 -> "7185550000";
                        case 3 -> "6145550000";
                        case 4 -> "4405550000";
                        case 5 -> "7405550000";
                        default -> "0000000000";
                    });
            System.out.println(customer);
        }

    }
}
