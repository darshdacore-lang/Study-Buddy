import java.util.ArrayList;
import java.util.List;

public class Tasks {

    private List<String> taskList;

    public Tasks() {
        taskList = new ArrayList<>();
    }

    public void addTask(String task) {
        taskList.add(task);
        System.out.println("Task added: " + task);
    }

    public void viewTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    public void deleteTask(int taskNumber) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available to delete.");
        } else if (taskNumber < 1 || taskNumber > taskList.size()) {
            System.out.println("Invalid task number.");
        } else {
            String removedTask = taskList.remove(taskNumber - 1);
            System.out.println("Deleted task: " + removedTask);
        }
    }
}
