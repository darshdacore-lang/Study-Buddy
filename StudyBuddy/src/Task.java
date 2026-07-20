public class Task {
    private String description;
    private String priority; // HIGH, MEDIUM, LOW
    private boolean completed;
    private String dueDate;

    public Task(String description, String priority) {
        this(description, priority, "", false);
    }

    public Task(String description, String priority, String dueDate) {
        this(description, priority, dueDate, false);
    }

    public Task(String description, String priority, boolean completed) {
        this(description, priority, "", completed);
    }

    public Task(String description, String priority, String dueDate, boolean completed) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toFileFormat() {
        return description + "|" + priority + "|" + completed + "|" + dueDate;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 3) {
            return new Task(parts[0], parts[1], false);
        } else if (parts.length >= 4) {
            return new Task(parts[0], parts[1], parts[3], Boolean.parseBoolean(parts[2]));
        }
        return null;
    }

    @Override
    public String toString() {
        String status = completed ? "[✓]" : "[ ]";
        String priorityLabel = priority.length() > 1 ? priority.substring(0, 1) : priority;
        String dueLabel = dueDate == null || dueDate.isEmpty() ? "" : " — Due: " + dueDate;
        return status + " (" + priorityLabel + ") " + description + dueLabel;
    }
}
