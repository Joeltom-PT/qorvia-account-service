package com.qorvia.accountservice.client;

import com.qorvia.accountservice.dto.message.StripeAccountOnboardingRequestMessage;
import com.qorvia.accountservice.dto.message.reponse.StripeAccountOnboardingRequestMessageResponse;
import com.qorvia.accountservice.messaging.RabbitMQSender;
import com.qorvia.accountservice.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentRabbitMqClient {

    private final RabbitMQSender rabbitMQSender;

    public StripeAccountOnboardingRequestMessageResponse generateAccountLinkForOnboarding(Long organizerId, String email) throws IOException {
        StripeAccountOnboardingRequestMessage message = new StripeAccountOnboardingRequestMessage();
        message.setEmail(email);
        message.setOrganizerId(organizerId);
        try {
            return rabbitMQSender.sendRpcMessage(
                    AppConstants.PAYMENT_SERVICE_RPC_QUEUE,
                    AppConstants.PAYMENT_SERVICE_RPC_EXCHANGE,
                    AppConstants.PAYMENT_SERVICE_RPC_ROUTING_KEY,
                    message,
                    StripeAccountOnboardingRequestMessageResponse.class);

        } catch (TimeoutException | IOException e) {
            log.error("Error generating account link for onboarding", e);
            return null;
        }
    }

}
