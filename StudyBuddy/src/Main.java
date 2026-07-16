import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tasks tasks = new Tasks();

        while (true) {
            System.out.println("\n=== Study Buddy ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter task: ");
                String task = scanner.nextLine();
                tasks.addTask(task);
            } else if (choice == 2) {
                tasks.viewTasks();
            } else if (choice == 3) {
                tasks.viewTasks();
                System.out.print("Enter task number to delete: ");
                int taskNumber = scanner.nextInt();
                scanner.nextLine();
                tasks.deleteTask(taskNumber);
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}