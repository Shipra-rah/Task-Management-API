package com.shipra.Dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserDto {
    private String email;
    private String password;
}
