import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duedate {
    public static boolean isValidDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return true;
        }

        try {
            LocalDate.parse(date.trim());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String calculateDueDate(String startDate, int daysToAdd) {
        LocalDate date = LocalDate.parse(startDate.trim());
        return date.plusDays(daysToAdd).toString();
    }

    public static void main(String[] args) {
        Duedate dueDate = new Duedate();
        String formattedDate = dueDate.calculateDueDate("2024-06-01", 10);
        System.out.println("The due date is: " + formattedDate);
    }
}