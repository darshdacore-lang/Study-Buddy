import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.*;

public class Tasks {
    private List<Task> taskList;
    private static final String FILE_NAME = "tasks.txt";

    public Tasks() {
        taskList = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String description, String priority, String dueDate) {
        Task task = new Task(description, priority, dueDate);
        taskList.add(task);
        System.out.println("✓ Task added: " + description + " (Priority: " + priority + ")");
        saveTasks();
    }

    public void viewTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n=== All Tasks ===");
            List<Task> sortedTasks = getSortedTasks(taskList);
            for (int i = 0; i < sortedTasks.size(); i++) {
                System.out.println((i + 1) + ". " + sortedTasks.get(i));
            }
        }
    }

    public void viewActiveTasks() {
        List<Task> activeTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (!task.isCompleted()) {
                activeTasks.add(task);
            }
        }

        if (activeTasks.isEmpty()) {
            System.out.println("No active tasks!");
        } else {
            System.out.println("\n=== Active Tasks ===");
            List<Task> sortedActiveTasks = getSortedTasks(activeTasks);
            for (int i = 0; i < sortedActiveTasks.size(); i++) {
                System.out.println((i + 1) + ". " + sortedActiveTasks.get(i));
            }
        }
    }

    public void markTaskComplete(int taskNumber) {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            System.out.println("Invalid task number.");
        } else {
            Task task = taskList.get(taskNumber - 1);
            task.setCompleted(true);
            System.out.println("✓ Task marked complete: " + task.getDescription());
            saveTasks();
        }
    }

    public void deleteTask(int taskNumber) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available to delete.");
        } else if (taskNumber < 1 || taskNumber > taskList.size()) {
            System.out.println("Invalid task number.");
        } else {
            Task removedTask = taskList.remove(taskNumber - 1);
            System.out.println("✗ Deleted task: " + removedTask.getDescription());
            saveTasks();
        }
    }

    public void editTask(int taskNumber, String newDescription) {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            System.out.println("Invalid task number.");
        } else {
            Task task = taskList.get(taskNumber - 1);
            task.setDescription(newDescription);
            System.out.println("✓ Task updated: " + newDescription);
            saveTasks();
        }
    }

    public void setPriority(int taskNumber, String priority) {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            System.out.println("Invalid task number.");
        } else {
            Task task = taskList.get(taskNumber - 1);
            task.setPriority(priority);
            System.out.println("✓ Priority updated to: " + priority);
            saveTasks();
        }
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            System.out.println("✓ Loaded " + taskList.size() + " task(s)");
        } catch (FileNotFoundException e) {
            System.out.println("Creating new task file...");
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private List<Task> getSortedTasks(List<Task> tasks) {
        List<Task> sorted = new ArrayList<>(tasks);
        Collections.sort(sorted, (t1, t2) -> {
            // Priority order: HIGH > MEDIUM > LOW
            int priorityOrder1 = getPriorityOrder(t1.getPriority());
            int priorityOrder2 = getPriorityOrder(t2.getPriority());

            if (priorityOrder1 != priorityOrder2) {
                return priorityOrder1 - priorityOrder2;
            }

            // If same priority, sort by due date (earliest first)
            String date1 = t1.getDueDate();
            String date2 = t2.getDueDate();

            if ((date1 == null || date1.isEmpty()) && (date2 == null || date2.isEmpty())) {
                return 0;
            }
            if (date1 == null || date1.isEmpty()) {
                return 1; // No date goes last
            }
            if (date2 == null || date2.isEmpty()) {
                return -1;
            }

            try {
                LocalDate ld1 = LocalDate.parse(date1);
                LocalDate ld2 = LocalDate.parse(date2);
                return ld1.compareTo(ld2);
            } catch (DateTimeParseException e) {
                return 0;
            }
        });
        return sorted;
    }

    private int getPriorityOrder(String priority) {
        if (priority == null)
            return 3;
        switch (priority.toUpperCase()) {
            case "HIGH":
                return 1;
            case "MEDIUM":
                return 2;
            case "LOW":
                return 3;
            default:
                return 3;
        }
    }
}
