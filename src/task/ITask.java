package task;

public interface ITask {
    String getType();
    String toFileString();
    boolean isDone();
    void markDone();
}
