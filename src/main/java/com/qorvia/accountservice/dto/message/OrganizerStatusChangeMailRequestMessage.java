package com.qorvia.accountservice.dto.message;

import lombok.Data;

@Data
public class OrganizerStatusChangeMailRequestMessage {
    private String type = "status-change";
    private String email;
    private String message;
    private String registerRequestStatus;
}