package com.sap.ae.dto.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "email is required")
    @NotBlank(message = "email must not be null")
    String email;

    @NotNull(message = " name is required ")
    @NotBlank(message = " name  must not be null ")

    String name;
    @NotNull(message = " password is required")
    @NotBlank(message = "password must not be null")
    String password;











}
