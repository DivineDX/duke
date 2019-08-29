package logic;

import task.Task;

import java.util.List;

/**
 * Contains the logic.TaskList and has the operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds Tasks to Task List
     *
     * @param task Task Obj to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printStr(sb.toString());
    }

    /**
     * Task to be marked as done
     *
     * @param taskNumStr String to be parsed as int
     * @throws DukeException If cannot parse string to int
     */
    public void markTask(String taskNumStr) throws DukeException { //mark as done. Need to check if already done, and if so throw exception
        int taskNum = Parser.parseTaskInt(taskNumStr, taskList);
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        Ui.printStr(sb.toString());
    }

    /**
     * Task to be deleted from list of tasks
     *
     * @param taskNumStr String to be parsed as int
     * @throws DukeException If cannot parse string to int
     */
    public void deleteTask(String taskNumStr) throws DukeException {
        int taskNum = Parser.parseTaskInt(taskNumStr, taskList);
        StringBuilder sb = new StringBuilder();
        Task t = taskList.get(taskNum - 1);
        sb.append("Noted. I've removed this task: \n");
        sb.append("  " + t + "\n");
        taskList.remove(taskNum - 1);
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printStr(sb.toString());
    }

}
