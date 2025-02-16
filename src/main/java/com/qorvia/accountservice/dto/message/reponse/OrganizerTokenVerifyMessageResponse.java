package com.qorvia.accountservice.dto.message.reponse;

import lombok.Data;

@Data
public class OrganizerTokenVerifyMessageResponse {
    private String email;
    private String message;
}
