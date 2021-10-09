package com.temzu.market_project.routinglib.dtos;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;
}