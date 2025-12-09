package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.client.base.FiatClient;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.FiatCashOutRequest;
import com.coins.api.model.FiatCashOutResponse;
import com.coins.api.model.FiatChannelConfigRequest;
import com.coins.api.model.FiatChannelConfigResponse;
import com.coins.api.model.FiatOrderDetailsRequest;
import com.coins.api.model.FiatOrderCommonResponse;
import com.coins.api.model.OpenApiQrCodeGenerateRequest;
import com.coins.api.model.OpenApiStaticQrCodeGenResponse;
import com.coins.api.model.QueryFiatOrderRequest;
import com.coins.api.model.OpenApiStaticQrCodeGenerateRequest;
import com.coins.api.model.OpenApiUpdateQrCodeRequest;
import com.coins.api.model.GetQrCodeRequest;
import com.coins.api.model.GetStaticQrCodeListRequest;
import com.coins.api.model.FiatCancelQrCodeRequest;
import com.coins.api.model.OpenApiQrCodeGenerateResponse;
import com.coins.api.model.OpenApiUpdateQrCodeResponse;
import com.coins.api.model.OpenApiQrCodeResponse;
import com.coins.api.model.OpenApiCancelQrCodeResponse;
import com.coins.api.model.WebPageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Example usage of the Fiat API
 */
public class FiatApiExample {
    private static final Logger log = LoggerFactory.getLogger(FiatApiExample.class);
    
    public static void main(String[] args) {
        CoinsApiConfig config = CoinsApiConfig.builder()
                                              .apiKey("sN7I8WSudxSNlVJztTaZf7nfIzhdbeitZ8EWlOnn9qUJm0RvznW03DmB8IM3VTik")
                                              .secretKey("J6ZyMS8RKPFLSm4DQ488UwzuSP26agbYax1TphnrB13QoxKUiEMOfduWXpPJYHYw")
                                              .baseUrl("https://api.9001.pl-qa.coinsxyz.me")
                                              .recvWindow(5000)
                                              .build();


        // Create the main API client
        CoinsApiClient client = new CoinsApiClient(config);
        FiatClient fiatClient = client.fiat();
        
        try {
            // Example 1: Get supported fiat channels for cash-in
            log.info("=== Getting Supported Fiat Channels (Cash-In) ===");
            FiatChannelConfigRequest channelRequest = FiatChannelConfigRequest.builder()
                .transactionType("1") // 1 for cash-in, -1 for cash-out
                .currency("PHP")
                .build();
            List<FiatChannelConfigResponse> channels = fiatClient.getSupportedChannels(channelRequest);
            log.info("Supported cash-in channels: {}", channels);

            // Example 2: Get supported fiat channels for cash-out
            log.info("=== Getting Supported Fiat Channels (Cash-Out) ===");
            FiatChannelConfigRequest cashOutChannelRequest = FiatChannelConfigRequest.builder()
                .transactionType("-1") // -1 for cash-out
                .currency("PHP")
                .amount("100.00")
                .build();
            List<FiatChannelConfigResponse> cashOutChannels = fiatClient.getSupportedChannels(cashOutChannelRequest);
            log.info("Supported cash-out channels: {}", cashOutChannels);

            // Example 3: Get fiat transaction history
            log.info("=== Getting Fiat Transaction History ===");
            QueryFiatOrderRequest historyRequest = QueryFiatOrderRequest.builder()
                .page(1)
                .size(10)
                .fiatCurrency("PHP")
                .build();
            WebPageResponse<FiatOrderCommonResponse> history = fiatClient.getTransactionHistory(historyRequest);
            log.info("Transaction history: {}", history);

            // Example 4: Get fiat order details by internal order ID
            log.info("=== Getting Fiat Order Details ===");
            List<FiatOrderCommonResponse> fiatOrderCommonResponse = history.getData();
            if (fiatOrderCommonResponse != null && !fiatOrderCommonResponse.isEmpty()) {
                FiatOrderCommonResponse order = fiatOrderCommonResponse.get(0);
                FiatOrderDetailsRequest orderRequest = FiatOrderDetailsRequest.builder()
                                                                              .internalOrderId(order.getInternalOrderId())
                                                                              .build();
                FiatOrderCommonResponse orderDetails = fiatClient.getOrderDetails(orderRequest);
                log.info("Order details: {}", orderDetails);
            }

            // Example 5: Execute fiat cash out (withdrawal)
            log.info("=== Executing Fiat Cash Out ===");
            Map<String,String> extendInfo = Map.of("recipientName", "sandy openapitest", "recipientAccountNumber", "123456789");
            FiatCashOutRequest cashOutRequest = FiatCashOutRequest.builder()
                                                                  .internalOrderId("cashout-" + System.currentTimeMillis())
                                                                  .currency("PHP")
                                                                  .amount("100.00")
                                                                  .channelName("SWIFTPAY_PESONET")
                                                                  .channelSubject("gcash")
                                                                  .extendInfo(extendInfo)
                                                                  .build();
            //FiatCashOutResponse cashOutResult = fiatClient.cashOut(cashOutRequest);
            //log.info("Cash out result: {}", cashOutResult);

            // Example 6: Generate QR code for payment
            log.info("=== Generating QR Code for Payment ===");
            OpenApiQrCodeGenerateRequest qrRequest = OpenApiQrCodeGenerateRequest.builder()
                                                                                 .requestId("qr-" + System.currentTimeMillis())
                                                                                 .source("payment-app")
                                                                                 .amount(new BigDecimal("50.00"))
                                                                                 .currency("PHP")
                                                                                 .remark("Payment for services")
                                                                                 .expiredSeconds("1800") // 30 minutes
                                                                                 .qrCodeMerchantName("My Store")
                                                                                 .build();
            OpenApiQrCodeGenerateResponse qrCode = fiatClient.generateQrCode(qrRequest);
            log.info("Generated QR code: {}", qrCode);

            // Example 7: Get QR code information
            log.info("=== Getting QR Code Information ===");
            GetQrCodeRequest getQrRequest = GetQrCodeRequest.builder()
                                                            .requestId(qrCode.getRequestId())
                                                            .build();
            OpenApiQrCodeGenerateResponse qrCodeInfo = fiatClient.getQrCode(getQrRequest);
            log.info("QR code info: {}", qrCodeInfo);

            // Example 8: Cancel QR code
            log.info("=== Canceling QR Code ===");
            FiatCancelQrCodeRequest cancelRequest = FiatCancelQrCodeRequest.builder()
                                                                           .referenceId(qrCode.getReferenceId())
                                                                           .build();
            OpenApiCancelQrCodeResponse cancelResult = fiatClient.cancelQrCode(cancelRequest);
            log.info("Cancel result: {}", cancelResult);

            // Example 9: Generate static QR code
            log.info("=== Generating Static QR Code ===");
            OpenApiStaticQrCodeGenerateRequest staticQrRequest = OpenApiStaticQrCodeGenerateRequest.builder()
                .requestId("static-qr-" + System.currentTimeMillis())
                .source("my-store")
                .currency("PHP")
                .remark("Static QR for store")
                .qrCodeMerchantName("My Store Name")
                .build();
            //OpenApiStaticQrCodeGenResponse staticQrCode = fiatClient.generateStaticQrCode(staticQrRequest);
            //log.info("Generated static QR code: {}", staticQrCode);

            // Example 10: Get static QR code list
            log.info("=== Getting Static QR Code List ===");
            GetStaticQrCodeListRequest listRequest = GetStaticQrCodeListRequest.builder()
                .status("ACTIVE")
                .build();
            List<OpenApiQrCodeResponse> staticQrCodes = fiatClient.getStaticQrCodeList(listRequest);
            log.info("Static QR codes: {}", staticQrCodes);

            // Example 11: Update QR code status
            log.info("=== Updating QR Code Status ===");
            if(staticQrCodes != null && !staticQrCodes.isEmpty()) {
                OpenApiUpdateQrCodeRequest updateRequest = OpenApiUpdateQrCodeRequest.builder()
                                                                                     .requestId(staticQrCodes.get(0).getRequestId())
                                                                                     .status("SUSPEND")
                                                                                     .build();
                OpenApiUpdateQrCodeResponse updateResult = fiatClient.updateQrCode(updateRequest);
                log.info("Update result: {}", updateResult);

            }

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
