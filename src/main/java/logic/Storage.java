package logic;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Deals with loading tasks and saving tasks in file
 */

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and scans text file, convert them to Task Objs to be added
     *
     * @return List of Tasks loaded from text file
     * @throws DukeException If encounter file creation/parsing problems
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            File f = new File(filePath); //hardCoded file directory
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strArr = line.split(Pattern.quote(" | "));
                Task task = readAndCreateTask(strArr);
                taskList.add(task);
            }
            Ui.printStr("Data is loaded from data/taskList.txt");
        } catch (FileNotFoundException e) {
            Ui.printStr("File not found, data not loaded!\nCreating new file taskList.txt");
            File f = new File(filePath);
            try {
                f.createNewFile(); //creates new text file
            } catch (IOException ioE) {
                Ui.printStr(ioE.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Creates task from reading the string from text file. Called from load()
     *
     * @param textArr String Array obtained after splitted
     * @return Task Object to be added to TaskList
     * @throws DukeException If unable to parse
     */
    private Task readAndCreateTask(String[] textArr) throws DukeException {
        String type = textArr[0];
        Task task = null;
        boolean done = textArr[1].equals("1"); //1 means done
        switch (type) {
        case "T": //todo
            task = new ToDo(done, textArr[2]);
            break;
        case "D": //deadline
            LocalDateTime deadline = Parser.parseDateTime(textArr[3]);
            task = new Deadlines(done, textArr[2], deadline);
            break;
        case "E": //event
            String[] startEndStr = textArr[3].split(" - ");
            LocalDateTime start = Parser.parseDateTime(startEndStr[0]);
            LocalDateTime end = Parser.parseDateTime(startEndStr[1]);
            task = new Events(done, textArr[2], start, end);
            break;
        default:
            break;
        }
        return task;
    }

    /**
     * Updates text file after command execution
     *
     * @param taskList List of Tasks in logic.TaskList
     */
    public void updateFile(TaskList taskList) {
        List<Task> list = taskList.getTaskList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            sb.append(t.toFileString());
            if (i != list.size() - 1) { //final item, dont add new line
                sb.append("\n");
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            Ui.printStr("Cannot write to file: " + e.getMessage());
        }
    }
}