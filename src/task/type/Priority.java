package task.type;
import task.TaskBase;;

public class Priority extends TaskBase{
    public Priority(String title) {
        super(title);
    }
    
    @Override
    public String getType() {
        return "Priority";
    }

    @Override
    public String toFileFormat() {
        return getType() + "|" + title + "|" + isCompleted;
    }
}
