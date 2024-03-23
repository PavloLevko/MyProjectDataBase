package org.example;

import org.example.Menu.Menu;

import java.util.Scanner;

public class Main {
    private static boolean isRun = true;
    private static Menu menu = new Menu();

    public static void main(String[] args) {

        while (isRun) {
            menu.sayHello();
            int input = menu.scan();
            if (input == 0) {
                isRun = false;
                break;
            } else {
                menu.logic(input);
            }
        }
    }
}

