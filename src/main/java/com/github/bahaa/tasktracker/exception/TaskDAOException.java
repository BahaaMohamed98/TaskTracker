package com.github.bahaa.tasktracker.exception;

public class TaskDAOException extends RuntimeException {
    public TaskDAOException(String message) {
        super(message);
    }

    public TaskDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
