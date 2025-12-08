package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.client.base.InvoicePaymentClient;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.CreatePaymentRequestRequest;
import com.coins.api.model.GetPaymentRequestRequest;
import com.coins.api.model.PaymentRequestListResponse;
import com.coins.api.model.PaymentRequestOperationRequest;
import com.coins.api.model.PaymentRequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

/**
 * Example usage of the Payment API
 */
public class InvoicePaymentExample {
    private static final Logger log = LoggerFactory.getLogger(InvoicePaymentExample.class);
    
    public static void main(String[] args) {
        // Configure the API client
        CoinsApiConfig config = CoinsApiConfig.builder()
                                              .apiKey("your-api-key")
                                              .secretKey("your-secret-key")
                                              .baseUrl("https://api.pro.coins.ph")  // Use appropriate base URL
                                              .recvWindow(5000)  // Optional: request timeout window in milliseconds
                                              .build();

        // Create the main API client
        CoinsApiClient client = new CoinsApiClient(config);
        InvoicePaymentClient paymentClient = client.payment();
        
        try {
            // Example 1: Create payment request
            log.info("=== Creating Payment Request ===");
            CreatePaymentRequestRequest createRequest = CreatePaymentRequestRequest.builder()
                .payerContactInfo("jennins@coins.ph")
                .receivingAccount("PHP")
                .amount(new BigDecimal("100"))
                .message("Payment for services")
                .supportedPaymentCollectors("[\"coins_peso_wallet\"]")
                .build();
            PaymentRequestResponse paymentResponse = paymentClient.createPaymentRequest(createRequest);
            log.info("Created payment request: {}", paymentResponse);
            
            // Example 2: Get payment request details
            log.info("=== Getting Payment Request Details ===");
            GetPaymentRequestRequest getRequest = GetPaymentRequestRequest.builder()
                .id(paymentResponse.getPaymentRequest().getId())
                .limit(1)
                .build();
            PaymentRequestListResponse requestDetails = paymentClient.getPaymentRequests(getRequest);
            log.info("Payment request details: {}", requestDetails);
            
            // Example 3: Send payment request reminder
            log.info("=== Sending Payment Request Reminder ===");
            PaymentRequestOperationRequest reminderRequest = PaymentRequestOperationRequest.builder()
                .id(paymentResponse.getPaymentRequest().getId())
                .build();
            Boolean reminderResult = paymentClient.sendPaymentRequestReminder(reminderRequest);
            log.info("Reminder result: {}", reminderResult);
            
            // Example 4: Create another payment request with different parameters
            log.info("=== Creating Another Payment Request ===");
            CreatePaymentRequestRequest anotherCreateRequest = CreatePaymentRequestRequest.builder()
                .payerContactInfo("jennins@coins.ph")
                .receivingAccount("PHP")
                .amount(new BigDecimal("200"))
                .message("Monthly subscription")
                .supportedPaymentCollectors("[\"coins_peso_wallet\"]")
                .build();
            PaymentRequestResponse anotherResponse = paymentClient.createPaymentRequest(anotherCreateRequest);
            log.info("Another payment request: {}", anotherResponse);
            
            // Example 5: Cancel payment request
            log.info("=== Cancelling Payment Request ===");
            PaymentRequestOperationRequest cancelRequest = PaymentRequestOperationRequest.builder()
                .id(anotherResponse.getPaymentRequest().getId())
                .build();
            PaymentRequestResponse cancelResult = paymentClient.cancelPaymentRequest(cancelRequest);
            log.info("Cancel result: {}", cancelResult);
            
            // Example 6: Get cancelled payment request details
            log.info("=== Getting Cancelled Payment Request Details ===");
            GetPaymentRequestRequest getCancelledRequest = GetPaymentRequestRequest.builder()
                .id(anotherResponse.getPaymentRequest().getId())
                .limit(1)
                .build();
            PaymentRequestListResponse cancelledRequests = paymentClient.getPaymentRequests(getCancelledRequest);
            log.info("Cancelled payment request: {}", cancelledRequests);
            
        } catch (CoinsApiException e) {
            log.error("API Error: {}", e.getMessage());
            if (e.getCode() != -1) {
                log.error("Error Code: {}", e.getCode());
            }
            log.error("Exception details:", e);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
        }
    }
}
