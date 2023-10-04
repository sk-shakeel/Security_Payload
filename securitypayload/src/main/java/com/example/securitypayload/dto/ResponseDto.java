package com.example.securitypayload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String unHashedPassword;
    private String hashedPassword;
}
