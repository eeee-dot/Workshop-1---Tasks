package pl.coderslab;

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
}
