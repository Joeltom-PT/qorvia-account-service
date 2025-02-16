package com.qorvia.accountservice.dto.message;

import lombok.Data;

@Data
public class OrganizerEmailVerificationRequestMessage {
    private String type = "verify-email";
    private String email;
}