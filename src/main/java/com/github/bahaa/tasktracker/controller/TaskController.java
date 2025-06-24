package com.github.bahaa.tasktracker.controller;

import com.github.bahaa.tasktracker.database.dao.TaskDAO;
import com.github.bahaa.tasktracker.database.model.Task;

import java.util.List;

public class TaskController {
    private final TaskDAO dao;

    public TaskController(TaskDAO dao) {
        this.dao = dao;
    }

    public Task addTask(String title, String description) {
        return dao.save(new Task(title, description));
    }

    public Task getTask(int id) {
        return dao.findById(id);
    }

    public void updateTask(Task task) {
        dao.update(task);
    }

    public void deleteTask(int id) {
        dao.deleteById(id);
    }

    public void markTaskDone(int id) {
        Task task = getTask(id);
        task.setDone(true);
        dao.update(task);
    }

    public void markTaskTodo(int id) {
        Task task = getTask(id);
        task.setDone(false);
        dao.update(task);
    }

    public List<Task> getAllTasks() {
        return dao.findAll();
    }

    public List<Task> getDoneTasks() {
        return dao.findByStatus(true);
    }

    public List<Task> getTodoTasks() {
        return dao.findByStatus(false);
    }
}
