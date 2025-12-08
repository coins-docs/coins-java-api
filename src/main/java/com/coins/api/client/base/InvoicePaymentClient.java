package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.CreatePaymentRequestRequest;
import com.coins.api.model.GetPaymentRequestRequest;
import com.coins.api.model.PaymentRequestListResponse;
import com.coins.api.model.PaymentRequestOperationRequest;
import com.coins.api.model.PaymentRequestResponse;
import com.coins.api.util.ValidationUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Locale;

/**
 * Client for Payment Request API operations
 */
public class InvoicePaymentClient {
    
    // API endpoint constants
    private static final String PAYMENT_REQUEST_ENDPOINT = "openapi/v3/payment-request/payment-requests";
    private static final String GET_PAYMENT_REQUEST_ENDPOINT = "openapi/v3/payment-request/get-payment-request";
    private static final String CANCEL_PAYMENT_REQUEST_ENDPOINT = "openapi/v3/payment-request/delete-payment-request";
    private static final String PAYMENT_REQUEST_REMINDER_ENDPOINT = "openapi/v3/payment-request/payment-request-reminder";
    
    private final CoinsApiConfig config;
    private final HttpClient httpClient;
    
    public InvoicePaymentClient(CoinsApiConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);
    }
    
    /**
     * Create a new payment request
     * 
     * @param request Create payment request parameters
     * @return Created payment request response
     * @throws CoinsApiException if the API call fails
     */
    public PaymentRequestResponse createPaymentRequest(CreatePaymentRequestRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("payer_contact_info=").append(request.getPayerContactInfo());
        queryBuilder.append("&receiving_account=").append(request.getReceivingAccount());
        queryBuilder.append("&amount=").append(request.getAmount().toString());
        queryBuilder.append("&message=").append(request.getMessage());
        
        if (request.getSupportedPaymentCollectors() != null) {
            queryBuilder.append("&supported_payment_collectors=").append(request.getSupportedPaymentCollectors());
        }
        
        if (request.getExpiresAt() != null) {
            queryBuilder.append("&expires_at=").append(request.getExpiresAt());
        }
        
        return httpClient.post(PAYMENT_REQUEST_ENDPOINT, queryBuilder.toString(), new TypeReference<PaymentRequestResponse>() {});
    }
    
    /**
     * Get payment request details
     * 
     * @param request Get payment request parameters
     * @return Payment request list response
     * @throws CoinsApiException if the API call fails
     */
    public PaymentRequestListResponse getPaymentRequests(GetPaymentRequestRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request.getId() != null) {
            queryBuilder.append("id=").append(request.getId());
            hasParams = true;
        }
        if (request.getStartTime() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("start_time=").append(request.getStartTime());
            hasParams = true;
        }
        if (request.getEndTime() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("end_time=").append(request.getEndTime());
            hasParams = true;
        }
        if (request.getLimit() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("limit=").append(request.getLimit());
        }
        
        return httpClient.get(GET_PAYMENT_REQUEST_ENDPOINT, queryBuilder.toString(), new TypeReference<PaymentRequestListResponse>() {});
    }
    
    /**
     * Cancel a payment request
     * 
     * @param request Payment request operation parameters
     * @return Cancellation result
     * @throws CoinsApiException if the API call fails
     */
    public PaymentRequestResponse cancelPaymentRequest(PaymentRequestOperationRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        String queryString = "id=" + request.getId();
        return httpClient.post(CANCEL_PAYMENT_REQUEST_ENDPOINT, queryString, new TypeReference<PaymentRequestResponse>() {});
    }

    /**
     * Send payment request reminder
     * 
     * @param request Payment request operation parameters
     * @return Reminder result
     * @throws CoinsApiException if the API call fails
     */
    public Boolean sendPaymentRequestReminder(PaymentRequestOperationRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        String queryString = "id=" + request.getId();
        return httpClient.post(PAYMENT_REQUEST_REMINDER_ENDPOINT, queryString, new TypeReference<Boolean>() {});
    }
}
