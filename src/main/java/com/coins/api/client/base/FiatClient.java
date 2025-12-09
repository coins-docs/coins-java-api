package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.APIResponse;
import com.coins.api.model.FiatCancelQrCodeRequest;
import com.coins.api.model.FiatCashOutRequest;
import com.coins.api.model.FiatCashOutResponse;
import com.coins.api.model.FiatChannelConfigRequest;
import com.coins.api.model.FiatChannelConfigResponse;
import com.coins.api.model.FiatOrderCommonResponse;
import com.coins.api.model.FiatOrderDetailsRequest;
import com.coins.api.model.GetQrCodeRequest;
import com.coins.api.model.GetStaticQrCodeListRequest;
import com.coins.api.model.OpenApiCancelQrCodeResponse;
import com.coins.api.model.OpenApiQrCodeGenerateRequest;
import com.coins.api.model.OpenApiQrCodeGenerateResponse;
import com.coins.api.model.OpenApiQrCodeResponse;
import com.coins.api.model.OpenApiStaticQrCodeGenResponse;
import com.coins.api.model.OpenApiStaticQrCodeGenerateRequest;
import com.coins.api.model.OpenApiUpdateQrCodeRequest;
import com.coins.api.model.OpenApiUpdateQrCodeResponse;
import com.coins.api.model.QueryFiatOrderRequest;
import com.coins.api.model.WebPageResponse;
import com.coins.api.util.ValidationUtil;
import com.coins.api.util.UrlBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Locale;

/**
 * Client for Fiat API operations
 */
public class FiatClient {
    
    // API endpoint constants
    private static final String SUPPORT_CHANNEL_ENDPOINT = "openapi/fiat/v1/support-channel";
    private static final String ORDER_DETAILS_ENDPOINT = "openapi/fiat/v1/details";
    private static final String TRANSACTION_HISTORY_ENDPOINT = "openapi/fiat/v1/history";
    private static final String CASH_OUT_ENDPOINT = "openapi/fiat/v1/cash-out";
    private static final String GENERATE_QR_CODE_ENDPOINT = "openapi/fiat/v1/generate_qr_code";
    private static final String GENERATE_STATIC_QR_CODE_ENDPOINT = "openapi/fiat/v1/generate/static/qr_code";
    private static final String CANCEL_QR_CODE_ENDPOINT = "openapi/fiat/v1/cancel_qr_code";
    private static final String UPDATE_QR_CODE_ENDPOINT = "openapi/fiat/v1/update/qr_code";
    private static final String GET_QR_CODE_ENDPOINT = "openapi/fiat/v1/get_qr_code";
    private static final String GET_STATIC_QR_CODE_LIST_ENDPOINT = "openapi/fiat/v1/get_qr_code/static/list";
    
    private final CoinsApiConfig config;
    private final HttpClient httpClient;

    public FiatClient(CoinsApiConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);
    }
    
    /**
     * Query supported fiat channels
     * 
     * @param request Channel configuration request
     * @return List of supported channels
     * @throws CoinsApiException if the API call fails
     */
    public List<FiatChannelConfigResponse> getSupportedChannels(FiatChannelConfigRequest request) 
            throws CoinsApiException {
        ValidationUtil.validate(request);
        APIResponse<List<FiatChannelConfigResponse>> response = httpClient.postWithBody(SUPPORT_CHANNEL_ENDPOINT, request, new TypeReference<APIResponse<List<FiatChannelConfigResponse>>>() {});

        return response.getData();
    }
    
    /**
     * Get fiat order details
     * 
     * @param request Order details request
     * @return Fiat order details
     * @throws CoinsApiException if the API call fails
     */
    public FiatOrderCommonResponse getOrderDetails(FiatOrderDetailsRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("internalOrderId", request.getInternalOrderId())
            .addParameter("externalOrderId", request.getExternalOrderId());

        APIResponse<FiatOrderCommonResponse> response = httpClient.get(ORDER_DETAILS_ENDPOINT, urlBuilder.buildQueryString(), new TypeReference<APIResponse<FiatOrderCommonResponse>>() {});

        return response.getData();
    }
    
    /**
     * Query fiat transaction history
     * 
     * @param request Transaction history query request
     * @return Transaction history response
     * @throws CoinsApiException if the API call fails
     */
    public WebPageResponse<FiatOrderCommonResponse> getTransactionHistory(QueryFiatOrderRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        WebPageResponse<FiatOrderCommonResponse> clientResponse = httpClient.postWithBody(TRANSACTION_HISTORY_ENDPOINT, request, new TypeReference<WebPageResponse<FiatOrderCommonResponse>>() {});

        return clientResponse;
    }
    
    /**
     * Execute fiat withdrawal operations (cash out)
     * 
     * @param request Cash out request
     * @return Cash out result
     * @throws CoinsApiException if the API call fails
     */
    public FiatCashOutResponse cashOut(FiatCashOutRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        APIResponse<FiatCashOutResponse> response = httpClient.postWithBody(CASH_OUT_ENDPOINT, request, new TypeReference<APIResponse<FiatCashOutResponse>>() {});
        return response.getData();
    }
    
    /**
     * Generate payment QR code
     * 
     * @param request QR code generation request
     * @return Generated QR code information
     * @throws CoinsApiException if the API call fails
     */
    public OpenApiQrCodeGenerateResponse generateQrCode(OpenApiQrCodeGenerateRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        // Use URL-encoded body format for proper signature calculation as per API documentation
        APIResponse  response = httpClient.postWithBody(GENERATE_QR_CODE_ENDPOINT, request, new TypeReference<APIResponse<OpenApiQrCodeGenerateResponse>>() {});
        return (OpenApiQrCodeGenerateResponse) response.getData();
    }
    
    /**
     * Generate static QR code
     * 
     * @param request Static QR code generation request
     * @return Generated static QR code information
     * @throws CoinsApiException if the API call fails
     */
    public OpenApiStaticQrCodeGenResponse generateStaticQrCode(OpenApiStaticQrCodeGenerateRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        APIResponse response = httpClient.postWithBody(GENERATE_STATIC_QR_CODE_ENDPOINT, request, new TypeReference<APIResponse<OpenApiStaticQrCodeGenResponse>>() {});

        return (OpenApiStaticQrCodeGenResponse) response.getData();
    }
    
    /**
     * Cancel existing QR code
     * 
     * @param request QR code cancellation request
     * @return Cancellation result
     * @throws CoinsApiException if the API call fails
     */
    public OpenApiCancelQrCodeResponse cancelQrCode(FiatCancelQrCodeRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        APIResponse response = httpClient.postWithBody(CANCEL_QR_CODE_ENDPOINT, request, new TypeReference<APIResponse<OpenApiCancelQrCodeResponse>>() {});

        return (OpenApiCancelQrCodeResponse) response.getData();
    }
    
    /**
     * Update QR code status
     * 
     * @param request QR code update request
     * @return Update result
     * @throws CoinsApiException if the API call fails
     */
    public OpenApiUpdateQrCodeResponse updateQrCode(OpenApiUpdateQrCodeRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        APIResponse response = httpClient.postWithBody(UPDATE_QR_CODE_ENDPOINT, request, new TypeReference<APIResponse<OpenApiUpdateQrCodeResponse>>() {});

        return (OpenApiUpdateQrCodeResponse) response.getData();
    }
    
    /**
     * Retrieve QR code information
     * 
     * @param request Get QR code request
     * @return QR code information
     * @throws CoinsApiException if the API call fails
     */
    public OpenApiQrCodeGenerateResponse getQrCode(GetQrCodeRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("requestId", request.getRequestId());

        APIResponse response = httpClient.get(GET_QR_CODE_ENDPOINT, urlBuilder.buildQueryString(), new TypeReference<APIResponse<OpenApiQrCodeGenerateResponse>>() {});
        return (OpenApiQrCodeGenerateResponse) response.getData();
    }
    
    /**
     * Get static QR code list
     * 
     * @param request Get static QR code list request
     * @return List of static QR codes
     * @throws CoinsApiException if the API call fails
     */
    public List<OpenApiQrCodeResponse> getStaticQrCodeList(GetStaticQrCodeListRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("status", request.getStatus());

        APIResponse response = httpClient.get(GET_STATIC_QR_CODE_LIST_ENDPOINT, urlBuilder.buildQueryString(), new TypeReference<APIResponse<List<OpenApiQrCodeResponse>>>() {});
        return (List<OpenApiQrCodeResponse>) response.getData();
    }
}
