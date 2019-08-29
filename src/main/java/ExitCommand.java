public class ExitCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.bye();
        System.exit(0);
    }
}
