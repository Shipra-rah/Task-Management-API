package com.shipra.Dto.UserDto;

import com.shipra.Domain.Role;
import com.shipra.Dto.TaskDto.TaskDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private Role role;
    private LocalDateTime created_at;
    private  LocalDateTime updated_at;
    private List<TaskDto> task;
}
