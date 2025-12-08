package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.GetBalancesResponse;
import com.coins.api.model.GetTransfersResponse;
import com.coins.api.model.TransferApiRequest;
import com.coins.api.model.TransferApiResponse;
import com.coins.api.model.TransferApiVo;
import com.coins.api.util.ValidationUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Client for P2P Transfer API operations
 */
public class P2pTransferClient {
    
    // API endpoint constants
    private static final String CRYPTO_ACCOUNTS_ENDPOINT = "openapi/account/v3/crypto-accounts";
    private static final String P2P_TRANSFER_ENDPOINT = "openapi/transfer/v3/transfers";
    private static final String QUERY_TRANSFER_ENDPOINT = "openapi/transfer/v3/transfers";
    
    private final CoinsApiConfig config;
    private final HttpClient httpClient;
    
    public P2pTransferClient(CoinsApiConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);
    }
    
    /**
     * Get crypto accounts information
     * 
     * @param currency Currency filter (optional)
     * @return List of crypto accounts
     * @throws CoinsApiException if the API call fails
     */
    public GetBalancesResponse getCryptoAccounts(String currency) throws CoinsApiException {
        StringBuilder queryBuilder = new StringBuilder();
        
        if (currency != null) {
            queryBuilder.append("currency=").append(currency);
        }

        GetBalancesResponse response = httpClient.get(CRYPTO_ACCOUNTS_ENDPOINT, queryBuilder.toString(), new TypeReference<GetBalancesResponse>() {});
        return response;
    }
    
    /**
     * Execute P2P transfer
     * 
     * @param request Transfer request with all parameters
     * @return Transfer result
     * @throws CoinsApiException if the API call fails
     */
    public TransferApiVo executeTransfer(@Valid TransferApiRequest request) throws CoinsApiException {
        // Validate request parameters with English locale
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        // Use postWithBody to send JSON request body
        TransferApiResponse response = httpClient.postWithBody(P2P_TRANSFER_ENDPOINT, request, new TypeReference<TransferApiResponse>() {});
        return response.getTransfer();
    }
    
    
    /**
     * Query transfer history
     * 
     * @param clientTransferId Client transfer ID (optional)
     * @param page Page number (optional)
     * @param perPage Results per page (optional)
     * @param toAddress Target address filter (optional)
     * @param fromAddress Source address filter (optional)
     * @param recvWindow Receive window (optional)
     * @return List of transfers
     * @throws CoinsApiException if the API call fails
     */
    public GetTransfersResponse queryTransfers(
            String clientTransferId,
            Integer page,
            Integer perPage,
            String toAddress,
            String fromAddress,
            Long recvWindow) throws CoinsApiException {
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (clientTransferId != null) {
            queryBuilder.append("client_transfer_id=").append(clientTransferId);
            hasParams = true;
        }
        if (page != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("page=").append(page);
            hasParams = true;
        }
        if (perPage != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("per_page=").append(perPage);
            hasParams = true;
        }
        if (toAddress != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("to_address=").append(toAddress);
            hasParams = true;
        }
        if (fromAddress != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("from_address=").append(fromAddress);
            hasParams = true;
        }
        if (recvWindow != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("recvWindow=").append(recvWindow);
        }
        
        return httpClient.get(QUERY_TRANSFER_ENDPOINT, queryBuilder.toString(), new TypeReference<GetTransfersResponse>() {});
    }
}
