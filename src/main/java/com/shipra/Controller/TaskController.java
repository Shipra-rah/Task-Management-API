package com.shipra.Controller;

import com.shipra.Dto.TaskDto.AddTaskDto;
import com.shipra.Dto.TaskDto.TaskDto;
import com.shipra.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAll(){
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDto addNewTask(@RequestBody AddTaskDto dto){
        return taskService.addNewTask(dto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody AddTaskDto dto){
        return taskService.updateTaskById(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean deletetask(@PathVariable Long id){
        return taskService.deleteTaskById(id);
    }
}
