package database.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final int id;
    private String title;
    private String description;
    private boolean done;
    private final LocalDateTime createdAt;

    public Task(int id, String title, String description, boolean done, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
    }

    public Task(String title, String description) {
        this(-1, title, description, false, LocalDateTime.now());
    }

    public Task(String title) {
        this(title, "");
    }

    public Task withId(int id) {
        return new Task(id, title, description, done, createdAt);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        String emoji = done ? "✅" : "⭕";
        String desc = description.isEmpty() ? "" : " — " + description;
        String base = String.format("%s  %d. %s%s", emoji, id, title, desc);
        String dateStr = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        int totalWidth = 50;
        int pad = Math.max(1, totalWidth - base.length() - dateStr.length());

        return base + " ".repeat(pad) + "| " + dateStr;
    }
}
