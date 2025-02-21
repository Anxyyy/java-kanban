package model;

import java.util.Objects;

public class Task implements Cloneable{
    private int id;
    private String name;
    protected Status status;
    private String description;

    public Task(int i, String name1, Status aNew, String description1){
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Task(String name, Status status, String description){
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Epic getEpic() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Task clone() {
        try {
            Task clonedTask = (Task) super.clone();
            clonedTask.setId(this.getId());
            clonedTask.setName(this.getName());
            clonedTask.setStatus(this.getStatus());
            clonedTask.setDescription(this.getDescription());
            return clonedTask;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description=" + description + '\'' + '}';
    }

}
