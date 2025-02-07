package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {
    public static String[][] tasks;

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
}
