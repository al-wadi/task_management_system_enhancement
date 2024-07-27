package com.example.task_management_system_enhancement.repositorie;

import com.example.task_management_system_enhancement.entitie.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wadi
 * @since 7/23/2024
 **/
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
}