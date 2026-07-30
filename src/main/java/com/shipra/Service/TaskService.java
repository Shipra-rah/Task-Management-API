package com.shipra.Service;

import com.shipra.Dto.TaskDto.AddTaskDto;
import com.shipra.Dto.TaskDto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTask();
    TaskDto getTaskById(Long id);
    TaskDto addNewTask(AddTaskDto dto);
    boolean deleteTaskById(Long id);
    TaskDto updateTaskById(Long id, AddTaskDto dto);
}
