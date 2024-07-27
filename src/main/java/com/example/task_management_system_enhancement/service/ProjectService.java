package com.example.task_management_system_enhancement.service;

import com.example.task_management_system_enhancement.entitie.Project;
import com.example.task_management_system_enhancement.entitie.Task;
import com.example.task_management_system_enhancement.entitie.User;
import com.example.task_management_system_enhancement.exception.ProjectServiceException;
import com.example.task_management_system_enhancement.repositorie.ProjectRepository;
import com.example.task_management_system_enhancement.repositorie.TaskRepository;
import com.example.task_management_system_enhancement.repositorie.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wadi
 * @since 7/23/2024
 *
 * service layer for managing projects, tasks, and users within projects
 **/
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project project) {
        project.setId(projectId);
        return projectRepository.save(project);
    }

    // Delete all tasks related with the project
    public void deleteProject(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        for (Task task : tasks) {
            taskRepository.delete(task);
        }
        projectRepository.deleteById(projectId);
    }

    //methods for managing tasks within a project
    public Task createTaskForProject(Long projectId, Task task) {
        Project project = getProjectById(projectId);
        task.setProject(project);
        return taskRepository.save(task);
    }

    public List<Task> getTasksForProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    //Update the task
    public Task updateTaskForProject(Long projectId, Long taskId, Task task) {
        Project project = getProjectById(projectId);
        Task existingTask = getTaskById(taskId);
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setProject(project);
        return taskRepository.save(existingTask);
    }

    //Delete the task
    public void deleteTaskFromProject(Long projectId, Long taskId) {
        Task task = getTaskById(taskId);
        taskRepository.delete(task);
    }

    //methods for managing users within a project

    // Assign the user to the task
    public User assignUserToTask(Long taskId, Long userId) {
        Task task = getTaskById(taskId);
        User user = getUserById(userId);
        task.setAssignedUser(user);
        return userRepository.save(user);
    }

    // Remove the user from the task
    public User removeUserFromTask(Long taskId, Long userId) {
        Task task = getTaskById(taskId);
        User user = getUserById(userId);
        task.setAssignedUser(null);
        return userRepository.save(user);
    }

    //methods to avoid code duplication

    public Task getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ProjectServiceException("Task not found with id: " + taskId));
        return task;
    }

    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ProjectServiceException("User not found with id: " + userId));
        return user;
    }

    public Project getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectServiceException("Project not found with id: " + projectId));
        return project;
    }
}
