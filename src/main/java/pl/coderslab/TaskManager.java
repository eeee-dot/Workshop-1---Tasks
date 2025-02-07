package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        String[] methods = {"add", "remove", "list", "exit"};
        for (String method : methods) {
            System.out.println(ConsoleColors.RESET + method);
        }
    }

    public static String[][] readTasks() {
        File file = new File("tasks.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Task file not found");
        }

        String[][] tasks = new String[1][1];
        int index = 0;
        try {
            while (scanner != null && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskData = line.split(",");
                tasks[index][0] = taskData[0]; // Description
                tasks[index][1] = taskData[1]; // Due date
                tasks[index][2] = taskData[2]; // Urgency
                index++;

            }
        } catch (NullPointerException e) {
            System.out.println("Task file empty");
        }
        return tasks;
    }
}
