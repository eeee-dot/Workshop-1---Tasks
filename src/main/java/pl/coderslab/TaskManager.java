package pl.coderslab;


import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        ReadFile.readTasks();
        boolean running = true;
        while (running) {
            running = printOptions(readFile);
        }
    }

    public static void printTasks(ReadFile readFile) {
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
                removeTask(readFile);
                break;
            case "list":
                printTasks(readFile);
                break;
            case "exit":
                return false;
            default:
                System.out.println(ConsoleColors.PURPLE_BOLD + "Invalid choice\n");
        }
        return true;
    }

    public static void removeTask(ReadFile readFile) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.RESET + "Please select an option: ");
        String choice = scanner.next();
        if (NumberUtils.isParsable(choice)) {
            boolean successfullyRemoved = ReadFile.removeRow(Integer.parseInt(choice));
            if (successfullyRemoved) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Removed row " + choice + "\n");
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Failed to remove row " + choice + "\n");
            }
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice\n");
            removeTask(readFile);
        }
    }
}
