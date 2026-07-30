package com.shipra.Dto.TaskDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private boolean status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
