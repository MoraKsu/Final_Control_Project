package view;

import java.util.Scanner;

public class AnimalRegistryView {
    private Scanner scanner;

    public AnimalRegistryView() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println("1. Завести новое животное");
        System.out.println("2. Увидеть список команд для животного");
        System.out.println("3. Обучить животное новым командам");
        System.out.println("4. Вывести список всех животных");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
