package com.qorvia.accountservice.dto.message.reponse;

import lombok.Data;

@Data
public class VerifyOtpMessageResponse {
    private Boolean isVerified;
    private String message;
}
