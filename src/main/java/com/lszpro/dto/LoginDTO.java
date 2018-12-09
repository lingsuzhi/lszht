package com.lszpro.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 登录
 */
@Data
public class LoginDTO {
    //账号
    @NotNull
    private String name;
    //密码
    @NotNull
    private String pwd;
}
