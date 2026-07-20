import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tasks tasks = new Tasks();

        while (true) {
            System.out.println("\n=== Study Buddy v3.0 ===");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Active Tasks");
            System.out.println("4. Mark Task Complete");
            System.out.println("5. Edit Task");
            System.out.println("6. Set Task Priority");
            System.out.println("7. Delete Task");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter task description: ");
                String description = scanner.nextLine();
                System.out.print("Set priority (HIGH/MEDIUM/LOW): ");
                String priority = scanner.nextLine().toUpperCase();
                System.out.print("Enter due date (yyyy-MM-dd, leave blank for none): ");
                String dueDate = scanner.nextLine().trim();

                if (!dueDate.isEmpty() && !Duedate.isValidDate(dueDate)) {
                    System.out.println("Invalid due date format. Use yyyy-MM-dd.");
                    dueDate = "";
                }

                if (isValidPriority(priority)) {
                    tasks.addTask(description, priority, dueDate);
                } else {
                    System.out.println("Invalid priority. Use HIGH, MEDIUM, or LOW.");
                }
            } else if (choice == 2) {
                tasks.viewTasks();
            } else if (choice == 3) {
                tasks.viewActiveTasks();
            } else if (choice == 4) {
                tasks.viewTasks();
                System.out.print("Enter task number to mark complete: ");
                int taskNumber = scanner.nextInt();
                scanner.nextLine();
                tasks.markTaskComplete(taskNumber);
            } else if (choice == 5) {
                tasks.viewTasks();
                System.out.print("Enter task number to edit: ");
                int taskNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new task description: ");
                String newDescription = scanner.nextLine();
                tasks.editTask(taskNumber, newDescription);
            } else if (choice == 6) {
                tasks.viewTasks();
                System.out.print("Enter task number: ");
                int taskNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Set new priority (HIGH/MEDIUM/LOW): ");
                String priority = scanner.nextLine().toUpperCase();

                if (isValidPriority(priority)) {
                    tasks.setPriority(taskNumber, priority);
                } else {
                    System.out.println("Invalid priority. Use HIGH, MEDIUM, or LOW.");
                }
            } else if (choice == 7) {
                tasks.viewTasks();
                System.out.print("Enter task number to delete: ");
                int taskNumber = scanner.nextInt();
                scanner.nextLine();
                tasks.deleteTask(taskNumber);
            } else if (choice == 8) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static boolean isValidPriority(String priority) {
        return priority.equals("HIGH") || priority.equals("MEDIUM") || priority.equals("LOW");
    }
}