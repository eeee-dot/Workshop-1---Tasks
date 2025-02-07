package pl.coderslab;

import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        boolean running = true;
        while (running) {
            running = printOptions(readFile);
        }
    }

    public static void printTasks(ReadFile readFile) {
        ReadFile.readTasks();
        String[][] tasks = ReadFile.tasks;
        if (tasks == null) {
            System.out.println(ConsoleColors.YELLOW + "No tasks found");
        } else {
            System.out.println();
            for (int i = 0; i < tasks.length; i++) {
                String formatedString = String.format("%d: %s, %s, %s", i + 1, tasks[i][0], tasks[i][1], tasks[i][2]);
                System.out.println(ConsoleColors.RESET + formatedString);
            }
        }
        System.out.println();
        printOptions(readFile);
    }

    public static boolean printOptions(ReadFile readFile) {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        String[] methods = {"add", "remove", "list", "exit"};
        for (String method : methods) {
            System.out.println(ConsoleColors.RESET + method);
        }
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "add":
                break;
            case "remove":
                break;
            case "list":
                printTasks(readFile);
            case "exit":
                return false;
            default:
                System.out.println(ConsoleColors.PURPLE_BOLD + "Invalid choice\n");
        }

        return true;
    }


}
