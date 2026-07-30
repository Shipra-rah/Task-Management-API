package com.shipra.Controller;

import com.shipra.Dto.UserDto.AddUserDto;
import com.shipra.Dto.UserDto.AuthTokenDto;
import com.shipra.Dto.UserDto.UserDto;
import com.shipra.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

     private final UserService userService;

     @PostMapping("/login")
     public AuthTokenDto login(@RequestBody AddUserDto dto){
         return userService.login(dto);
     }

    @PostMapping("/signup")
    public String signup(@RequestBody AddUserDto dto){
        return userService.signup(dto);
    }

}
