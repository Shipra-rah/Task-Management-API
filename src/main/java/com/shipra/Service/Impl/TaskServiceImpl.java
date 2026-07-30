package com.shipra.Service.Impl;

import com.shipra.Dto.TaskDto.AddTaskDto;
import com.shipra.Dto.TaskDto.TaskDto;
import com.shipra.Entity.Task;
import com.shipra.Entity.Users;
import com.shipra.Repo.TaskRepo;
import com.shipra.Repo.UserRepo;
import com.shipra.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    /**
     * Returns the currently logged-in user.
     */
    private Users getCurrentUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepo.findUsersByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<TaskDto> getAllTask() {

        Users user = getCurrentUser();

        return taskRepo.findByUser(user)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {

        Users user = getCurrentUser();

        Task task = taskRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return entityToDto(task);
    }

    @Override
    public TaskDto addNewTask(AddTaskDto dto) {

        Users user = getCurrentUser();

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(true);
        task.setCreated_at(LocalDateTime.now());
        task.setUpdated_at(null);
        task.setUser(user);

        Task savedTask = taskRepo.save(task);

        return entityToDto(savedTask);
    }

    @Override
    public TaskDto updateTaskById(Long id, AddTaskDto dto) {

        Users user = getCurrentUser();

        Task task = taskRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setUpdated_at(LocalDateTime.now());

        Task updatedTask = taskRepo.save(task);

        return entityToDto(updatedTask);
    }

    @Override
    public boolean deleteTaskById(Long id) {

        Users user = getCurrentUser();

        Task task = taskRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepo.delete(task);

        return true;
    }

    private TaskDto entityToDto(Task task) {

        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.isStatus());
        dto.setCreated_at(task.getCreated_at());
        dto.setUpdated_at(task.getUpdated_at());

        return dto;
    }
}