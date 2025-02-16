package com.qorvia.accountservice.dto.message;

import lombok.Data;

@Data
public class VerifyOtpMessage {
    private String type = "verify-otp";
    private String email;
    private String otp;
}
