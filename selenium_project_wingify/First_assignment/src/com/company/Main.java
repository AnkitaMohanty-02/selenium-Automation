package com.company;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks=sc.nextInt();
        int input;

        do{

            if(marks >=90 && marks <= 100){
                System.out.println("This is good");
            }else if(marks >=60 && marks < 90){
                System.out.println("This is also good");
            }else if(marks >=0 && marks < 60){
                System.out.println("This is good as well");
            }else {
                System.out.println("Invalid");
            }
            System.out.println("Want to continue ? (Yes(1) Or No(0))");
            input = sc.nextInt();
        }while(input == 1);
    }
}
