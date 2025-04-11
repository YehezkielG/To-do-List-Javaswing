package task;

public abstract class TaskBase {
    protected String title;
    protected boolean isCompleted;

    public TaskBase(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getName(){
        return this.title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markDone() {
        this.isCompleted = true;
    }

    public abstract String getType();
    public abstract String toFileFormat();
}
