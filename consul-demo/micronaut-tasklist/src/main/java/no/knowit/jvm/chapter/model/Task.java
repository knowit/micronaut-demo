package no.knowit.jvm.chapter.model;

public class Task {

    private long id;

    private long parent;

    private String description;

    private TaskStatus status;

    public Task() {
    }

    public Task(long id, long parent, String description, TaskStatus status) {
        this.id = id;
        this.parent = parent;
        this.description = description;
        this.status = status;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

}