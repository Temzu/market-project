package com.temzu.market_project.routinglib.clients;

import com.temzu.market_project.routinglib.dtos.AuthRequestDto;
import com.temzu.market_project.routinglib.dtos.AuthResponseDto;
import com.temzu.market_project.routinglib.dtos.SignUpRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ms-auth")
public interface AuthClient {
    @PostMapping("/signup")
    String signUp(@RequestBody SignUpRequestDto signUpRequest);

    @PostMapping("/login")
    AuthResponseDto login(@RequestBody AuthRequestDto request);
}
