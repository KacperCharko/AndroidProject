//package com.example.androidproject;
//
//
//
//
//import com.example.androidproject.database.Task;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class TaskStorage {
//    private static final TaskStorage ourInstance = new TaskStorage();
//    private List<Task> tasks;
//
//    private TaskStorage() {
//        tasks = new ArrayList<>();
//        for (int i = 0; i < 100; i++) { tasks.add(new Task("Task " + Integer.toString(i))); }
//    }
//
//    public static TaskStorage getInstance() {
//        return ourInstance;
//    }
//
//    public List<Task> getTasks() { return tasks; }
//    public Task getTask(UUID id) {
//        Task result = null;
//        for (Task task : tasks) {
//            if (task.getId().equals(id)) { result = task; }
//        }
//        return result;
//    }
//
//    public void addTask(Task task) {
//        tasks.add(task);
//    }
//}