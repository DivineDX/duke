package Task;

import Functionality.DukeException;

public abstract class Task {
    String description;
    boolean isDone;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void markDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task.Task is already done!");
        } else {
            this.isDone = true;
        }
    }

    public abstract String toFileString();
    public abstract String toString();
}