package task.type;
import task.TaskBase;

public class Daily extends TaskBase {
    public Daily(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "Daily";
    }

    @Override
    public String toFileFormat() {
        return getType() + "|" + title + "|" + isCompleted;
    }
}
