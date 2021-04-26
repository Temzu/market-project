package com.temzu.market_project.msauth.model.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String login;
    private String password;
}
