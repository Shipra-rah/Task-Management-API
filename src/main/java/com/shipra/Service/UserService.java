package com.shipra.Service;

import com.shipra.Dto.UserDto.AddUserDto;
import com.shipra.Dto.UserDto.AuthTokenDto;
import com.shipra.Dto.UserDto.UserDto;

public interface UserService {
    AuthTokenDto login(AddUserDto dto);
    String signup(AddUserDto dto);
}
