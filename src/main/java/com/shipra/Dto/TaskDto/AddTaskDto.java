package com.shipra.Dto.TaskDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTaskDto {
    private String title;
    private String description;
}
