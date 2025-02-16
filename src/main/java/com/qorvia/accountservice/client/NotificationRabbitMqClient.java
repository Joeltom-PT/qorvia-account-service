package com.qorvia.accountservice.client;

import com.qorvia.accountservice.dto.message.*;

import com.qorvia.accountservice.dto.message.reponse.OrganizerTokenVerifyMessageResponse;
import com.qorvia.accountservice.dto.message.reponse.VerifyOtpMessageResponse;
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
public class NotificationRabbitMqClient {

    private final RabbitMQSender rabbitMQSender;

    // Send OTP
    public void sendOtp(String email) {
        SendOtpMessage message = new SendOtpMessage();
        message.setEmail(email);
        rabbitMQSender.sendAsyncMessage(AppConstants.NOTIFICATION_SERVICE_ASYNC_QUEUE, AppConstants.NOTIFICATION_SERVICE_EXCHANGE, AppConstants.NOTIFICATION_SERVICE_ROUTING_KEY, message);
        log.info("Sent OTP request for email: {}", email);
    }

    // Verify OTP
    public VerifyOtpMessageResponse verifyOtp(String email, String otp) throws TimeoutException, IOException {
        VerifyOtpMessage message = new VerifyOtpMessage();
        message.setEmail(email);
        message.setOtp(otp);
        log.info("Verifying OTP for email: {}", email);
        return rabbitMQSender.sendRpcMessage(
                AppConstants.NOTIFICATION_SERVICE_RPC_QUEUE,
                AppConstants.NOTIFICATION_SERVICE_RPC_EXCHANGE,
                AppConstants.NOTIFICATION_SERVICE_RPC_ROUTING_KEY,
                message,
                VerifyOtpMessageResponse.class
        );
    }

    // Verify Email
    public void organizerEmailVerificationRequest(String email) {
        OrganizerEmailVerificationRequestMessage message = new OrganizerEmailVerificationRequestMessage();
        message.setEmail(email);
        rabbitMQSender.sendAsyncMessage(AppConstants.NOTIFICATION_SERVICE_ASYNC_QUEUE, AppConstants.NOTIFICATION_SERVICE_EXCHANGE, AppConstants.NOTIFICATION_SERVICE_ROUTING_KEY, message);
        log.info("Sent email verification request for email: {}", email);
    }

    // Verify Token
    public OrganizerTokenVerifyMessageResponse organizerTokenVerify(String token) throws TimeoutException, IOException {
        OrganizerEmailVerifyMessage message = new OrganizerEmailVerifyMessage();
        message.setToken(token);
        log.info("Verifying token: {}", token);
        return rabbitMQSender.sendRpcMessage(
                AppConstants.NOTIFICATION_SERVICE_RPC_QUEUE,
                AppConstants.NOTIFICATION_SERVICE_RPC_EXCHANGE,
                AppConstants.NOTIFICATION_SERVICE_RPC_ROUTING_KEY,
                message,
                OrganizerTokenVerifyMessageResponse.class
        );
    }

    // Status Change Email
    public void organizerStatusChangeMail(String email, String message, String status) {
        OrganizerStatusChangeMailRequestMessage requestMessage = new OrganizerStatusChangeMailRequestMessage();
        requestMessage.setRegisterRequestStatus(status);
        requestMessage.setMessage(message);
        requestMessage.setEmail(email);
        rabbitMQSender.sendAsyncMessage(AppConstants.NOTIFICATION_SERVICE_ASYNC_QUEUE, AppConstants.NOTIFICATION_SERVICE_EXCHANGE, AppConstants.NOTIFICATION_SERVICE_ROUTING_KEY, requestMessage);
        log.info("Sent status change email request for email: {}", email);
    }
}
