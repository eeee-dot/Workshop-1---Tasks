package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        boolean running = true;
        while(running) {
            running = printOptions();
        }
    }

    public static void printTasks() {
        String[][] tasks = readTasks();
        if (tasks == null) {
            System.out.println(ConsoleColors.YELLOW + "No tasks found");
        } else {
            System.out.println();
            for (String[] task : tasks) {
                System.out.println(ConsoleColors.RESET + task[0] + task[1] + ConsoleColors.RESET + task[2]);
            }
        }
    }

    public static boolean printOptions() {
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
                printTasks();
            case "exit":
                return false;
            default:
                System.out.println(ConsoleColors.PURPLE_BOLD + "Invalid choice");
        }
        return true;
    }

    public static String[][] readTasks() {
        Path path = Paths.get("tasks.csv");
        try {
            String[] fileContent = Files.readString(path).split("\\n");
            String[][] tasks = new String[fileContent.length][3];
            for (int i = 0; i < fileContent.length; i++) {
                String[] task = fileContent[i].split(",");
                tasks[i][0] = task[0];
                tasks[i][1] = task[1];
                tasks[i][2] = task[2];
            }
            return tasks;
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "Error while reading file");
        }
        return null;
    }
}
