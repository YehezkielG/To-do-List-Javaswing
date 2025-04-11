package task.type;
import task.TaskBase;

public class Regular extends TaskBase{
    public Regular(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "Regular";
    }

    @Override
    public String toFileFormat() {
        return getType() + "|" + title + "|" + isCompleted;
    }
}
