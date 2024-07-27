package com.example.task_management_system_enhancement.service;

import com.example.task_management_system_enhancement.entitie.Task;
import com.example.task_management_system_enhancement.exception.TaskServiceException;
import com.example.task_management_system_enhancement.repositorie.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wadi
 * @since 7/23/2024
 *
 * Service layer for managing tasks
 **/
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskServiceException("Task not found with ID: " + taskId));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task task) {
        task.setId(taskId);
        return taskRepository.save(task);
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //fetch all tasks related to specific project
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

}