package com.github.bahaa.tasktracker.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super("Task with id " + id + " not found");
    }
}
