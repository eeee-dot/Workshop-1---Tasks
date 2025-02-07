package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadFile {
    public static String[][] tasks;

    private ReadFile() {

    }


    public static void readTasks() {
        Path path = Paths.get("tasks.csv");
        try {
            String[] fileContent = Files.readString(path).split("\\n");
            String[][] content = new String[fileContent.length][3];
            for (int i = 0; i < fileContent.length; i++) {
                String[] task = fileContent[i].split(",");
                content[i][0] = task[0];
                content[i][1] = task[1];
                content[i][2] = task[2];
            }
            tasks = content;
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "Error while reading file");
        }
    }

    public static boolean removeRow(int index) {
        try {
            tasks = ArrayUtils.remove(tasks, index - 1);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public static void addRow(String[] data) {
        tasks = ArrayUtils.add(tasks, data);
    }

    public static void saveToFile() {
        Path path = Paths.get("tasks.csv");
        try {
            Files.writeString(path, "");
            for (String[] rowTask : tasks) {
                for (String task : rowTask) {
                    String format = String.format("%s,", task);
                    Files.writeString(path, format, StandardOpenOption.APPEND);
                }
                Files.writeString(path, "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "Error while saving file");
        }
    }

}
