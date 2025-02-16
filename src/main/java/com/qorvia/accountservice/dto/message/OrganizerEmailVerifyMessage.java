package com.qorvia.accountservice.dto.message;

import lombok.Data;

@Data
public class OrganizerEmailVerifyMessage {
    private String type = "verify-token";
    private String token;
}
