package util;

import java.io.*;
import java.util.ArrayList;

import task.TaskBase;
import task.type.*;

public class FileHandler {
    public void saveTask(TaskBase task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt", true))) {
            writer.write(task.toFileFormat());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<TaskBase> loadTasks() {
        ArrayList<TaskBase> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 3) continue;
                String type = parts[0];
                String title = parts[1];
                boolean isCompleted = Boolean.parseBoolean(parts[2]);
                TaskBase task;
                switch (type) {
                    case "Daily":
                        task = new Daily(title);
                        break;
                    case "Priority":
                        task = new Priority(title);
                        break;
                    default:
                        task = new Regular(title);
                }
                if (isCompleted) task.markDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    public void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            writer.write(""); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
