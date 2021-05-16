package com.temzu.market_project.msauth.controllers;

import com.temzu.market_project.msauth.entities.User;
import com.temzu.market_project.msauth.repositories.RoleRepository;
import com.temzu.market_project.msauth.services.UserService;
import com.temzu.market_project.mscore.interfaces.ITokenService;
import com.temzu.market_project.mscore.interfaces.RedisService;
import com.temzu.market_project.mscore.model.UserInfo;
import com.temzu.market_project.mscore.model.dtos.AuthRequestDto;
import com.temzu.market_project.mscore.model.dtos.AuthResponseDto;
import com.temzu.market_project.mscore.model.dtos.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getLogin())
                .role(user.getRole().getName())
                .build();
        String token = tokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @PostMapping("/logout")
    public String logout() {
        return "OK";
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String check() {
        return "OK!";
    }

}