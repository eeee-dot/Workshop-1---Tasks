package pl.coderslab;

import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        ReadFile.readTasks();
        while (true) {
            if (!printOptions()) break;
        }
    }

    public static void printTasks() {
        if (ReadFile.tasks == null) {
            System.out.println(ConsoleColors.YELLOW + "No tasks found");
        } else {
            System.out.println();
            String listHeaders = String.format(ConsoleColors.CYAN_BOLD +
                    "%-10s %-30s %11s %14s", "#", "Description", "Due date", "Importance");
            System.out.println(listHeaders);
            for (int i = 0; i < listHeaders.length()-5; i++){
                System.out.print('-');
            }

            System.out.println();
            for (int i = 0; i < ReadFile.tasks.length; i++) {
                String formatedString = String.format(
                        "%d: %-40s %10s %10s",
                        i + 1,
                        ReadFile.tasks[i][0],
                        ReadFile.tasks[i][1],
                        ReadFile.tasks[i][2]
                );

                System.out.println(ConsoleColors.RESET + formatedString);
            }
        }
        System.out.println();
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
                addTask(scanner);
                break;
            case "remove":
                removeTask(scanner);
                break;
            case "list":
                printTasks();
                break;
            case "exit":
                ReadFile.saveToFile();
                return false;
            default:
                System.out.println(ConsoleColors.PURPLE_BOLD + "Invalid choice\n");
        }
        return true;
    }

    public static void removeTask(Scanner scanner) {
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
            removeTask(scanner);
        }

    }

    public static void addTask(Scanner scanner) {
        String[] taskData = new String[3];
        taskData[0] = taskDescription(scanner);
        taskData[1] = taskDueDate(scanner);
        taskData[2] = taskImportance(scanner);

        ReadFile.addRow(taskData);
        System.out.println(ConsoleColors.YELLOW_BOLD + "Task added\n");
    }

    public static String taskDescription(Scanner scanner) {
        String taskDesc = "";
        do {
            System.out.println(ConsoleColors.RESET + "Provide task description: ");
            taskDesc = scanner.nextLine();
        } while (taskDesc.length() <= 4);

        return taskDesc;
    }

    public static String taskDueDate(Scanner scanner) {
        String taskDate = "";
        while (true) {
            System.out.println(ConsoleColors.RESET + "Provide task due date (YYYY-MM-DD):");
            taskDate = scanner.nextLine();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate providedDate = LocalDate.parse(taskDate, dateFormat);
                LocalDate today = LocalDate.now();
                if (today.isAfter(providedDate)) {
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Due date is after today's date");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "Incorrect date format");
            }
        }
        return taskDate;
    }

    public static String taskImportance(Scanner scanner) {
        String importance = "";
        do {
            System.out.println(ConsoleColors.RESET + "Is task important? (true/false): ");
            importance = scanner.nextLine();
        } while (!importance.equals("true") && !importance.equals("false"));

        return importance;
    }
}

