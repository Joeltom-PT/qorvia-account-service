package com.qorvia.accountservice.dto.message;

import lombok.Data;

@Data
public class SendOtpMessage {
    private String type = "send-otp";
    private String email;
}