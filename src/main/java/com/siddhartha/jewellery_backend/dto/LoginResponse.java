package com.siddhartha.jewellery_backend.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private Long employeeId;
    private String username;
    private String fullName;
    private String role;

    public LoginResponse(
            String token,
            Long employeeId,
            String username,
            String fullName,
            String role) {

        this.token = token;
        this.employeeId = employeeId;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}