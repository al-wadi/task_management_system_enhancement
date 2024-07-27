package com.example.task_management_system_enhancement;

import com.example.task_management_system_enhancement.entitie.Task;
import com.example.task_management_system_enhancement.repositorie.TaskRepository;
import com.example.task_management_system_enhancement.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskManagementSystemEnhancementApplicationTests {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertEquals(task, result);
    }
}