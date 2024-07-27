package com.example.task_management_system_enhancement.repositorie;

import com.example.task_management_system_enhancement.entitie.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wadi
 * @since 7/23/2024
 **/
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}