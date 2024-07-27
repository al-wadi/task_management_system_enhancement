package com.example.task_management_system_enhancement.service;

import com.example.task_management_system_enhancement.entitie.Task;
import com.example.task_management_system_enhancement.entitie.User;
import com.example.task_management_system_enhancement.exception.UserServiceException;
import com.example.task_management_system_enhancement.repositorie.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wadi
 * @since 7/23/2024
 *
 * Service layer for managing users and tasks
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserServiceException("User not found with ID: " + userId));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        if (!userRepository.existsById(userId)) {
            throw new UserServiceException("User not found with ID: " + userId);
        }
        user.setId(userId);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //fetch all tasks related to specific user
    public List<Task> getTasksForUser(Long userId) {
        User user = getUserById(userId);
        return user.getTasks();
    }
}