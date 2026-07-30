package com.shipra.Service.Impl;

import com.shipra.Domain.Role;
import com.shipra.Dto.UserDto.AddUserDto;
import com.shipra.Dto.UserDto.AuthTokenDto;
import com.shipra.Dto.UserDto.UserDto;
import com.shipra.Entity.Users;
import com.shipra.Repo.UserRepo;
import com.shipra.Security.JwtUtil;
import com.shipra.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthTokenDto login(AddUserDto dto) {
        Users user = userRepo.findUsersByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        AuthTokenDto token = new AuthTokenDto();
        token.setToken(jwtUtil.genrateToken(user.getEmail(), user.getRole()));
        return  token;
    }


    @Override
    public String signup(AddUserDto dto) {
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new RuntimeException(dto.getEmail() + " Is Already Exist.");
        }
        Users user = new Users();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(null);

        userRepo.save(user);
        return user.getEmail() +"Ragister Successfully.";
    }
}
