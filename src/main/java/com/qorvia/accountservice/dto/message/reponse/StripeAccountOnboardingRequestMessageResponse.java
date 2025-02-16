package com.qorvia.accountservice.dto.message.reponse;

import lombok.Data;

@Data
public class StripeAccountOnboardingRequestMessageResponse {
    private String url;
    private String message;
}
