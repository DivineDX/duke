package Commands;

import Functionality.*;

public class DeleteCommand extends Command {
    private String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(args); //deletes task obj
        storage.updateFile(tasks);
    }
}