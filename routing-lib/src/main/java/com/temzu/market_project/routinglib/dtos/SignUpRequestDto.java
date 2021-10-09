package com.temzu.market_project.routinglib.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {

    private String login;
    private String password;
    private String email;
}