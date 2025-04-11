package task;

import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<TaskBase> tasks;
    private final FileHandler fileHandler;

    public TaskManager() {
        this.fileHandler = new FileHandler();
        this.tasks = new ArrayList<>(fileHandler.loadTasks());
    }

    public List<TaskBase> getTasks() {
        return tasks;
    }

    public void addTask(TaskBase task) {
        tasks.add(task);
        fileHandler.saveTask(task); 
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveAll(); 
        }
    }

    public void setComplated(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();;
            saveAll(); 
        }
    }

    public void saveAll() {
        fileHandler.clearFile(); 
        for (TaskBase task : tasks) {
            fileHandler.saveTask(task);
        }
    }
}
