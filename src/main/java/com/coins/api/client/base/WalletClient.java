package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.AccountInfoResponse;
import com.coins.api.model.AddressWhitelistVo;
import com.coins.api.model.DepositAddress;
import com.coins.api.model.DepositAddressApiRequest;
import com.coins.api.model.DepositRecordVo;
import com.coins.api.model.GetTransactionHistoryRequest;
import com.coins.api.model.GetTransactionHistoryResponse;
import com.coins.api.model.WalletAssetConfigVo;
import com.coins.api.model.WithdrawApplyRequest;
import com.coins.api.model.WithdrawApplyVo;
import com.coins.api.model.WithdrawHistoryQueryRequest;
import com.coins.api.model.WithdrawRecordVo;
import com.coins.api.model.WithdrawWhitelistQueryRequest;
import com.coins.api.util.ValidationUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Locale;

/**
 * Client for Wallet API operations
 */
public class WalletClient {
    
    // API endpoint constants
    private static final String ACCOUNT_ENDPOINT = "openapi/v1/account";
    private static final String CONFIG_GETALL_ENDPOINT = "openapi/wallet/v1/config/getall";
    private static final String DEPOSIT_ADDRESS_ENDPOINT = "openapi/wallet/v1/deposit/address";
    private static final String DEPOSIT_HISTORY_ENDPOINT = "openapi/wallet/v1/deposit/history";
    private static final String WITHDRAW_HISTORY_ENDPOINT = "openapi/wallet/v1/withdraw/history";
    private static final String WITHDRAW_APPLY_ENDPOINT = "openapi/wallet/v1/withdraw/apply";
    private static final String TRANSACTION_HISTORY_ENDPOINT = "openapi/v1/asset/transaction/history";
    private static final String ADDRESS_WHITELIST_ENDPOINT = "openapi/wallet/v1/withdraw/address-whitelist";
    
    private final CoinsApiConfig config;
    private final HttpClient httpClient;
    
    public WalletClient(CoinsApiConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);
    }
    
    /**
     * Get account information
     * 
     * @param recvWindow Receive window (optional)
     * @return Account information
     * @throws CoinsApiException if the API call fails
     */
    public AccountInfoResponse getAccount(Long recvWindow) throws CoinsApiException {
        StringBuilder queryBuilder = new StringBuilder();
        if (recvWindow != null) {
            queryBuilder.append("recvWindow=").append(recvWindow);
        }
        
        return httpClient.get(ACCOUNT_ENDPOINT, queryBuilder.toString(), new TypeReference<AccountInfoResponse>() {});
    }
    
    /**
     * Get all wallet configurations
     * 
     * @return Wallet configurations
     * @throws CoinsApiException if the API call fails
     */
    public List<WalletAssetConfigVo>  getAllConfigs() throws CoinsApiException {
        return httpClient.get(CONFIG_GETALL_ENDPOINT, "", new TypeReference<List<WalletAssetConfigVo> >() {});
    }
    
    /**
     * Get deposit address
     *
     * @param request
     * @return Deposit address information
     * @throws CoinsApiException if the API call fails
     */
    public DepositAddress getDepositAddress(DepositAddressApiRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("coin=").append(request.getCoin());
        queryBuilder.append("&network=").append(request.getNetwork());
        
        return httpClient.get(DEPOSIT_ADDRESS_ENDPOINT, queryBuilder.toString(), new TypeReference<DepositAddress>() {});
    }
    
    /**
     * Get deposit history
     * 
     * @param coin Coin filter (optional)
     * @param txId Transaction ID filter (optional)
     * @param status Status filter (optional)
     * @param statuses Status list filter (optional)
     * @param startTime Start time filter (optional)
     * @param endTime End time filter (optional)
     * @param offset Offset for pagination (optional)
     * @param limit Limit for pagination (optional)
     * @return List of deposit records
     * @throws CoinsApiException if the API call fails
     */
    public List<DepositRecordVo> getDepositHistory(
            String coin,
            String txId,
            Integer status,
            String statuses,
            Long startTime,
            Long endTime,
            Integer offset,
            Integer limit) throws CoinsApiException {
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (coin != null) {
            queryBuilder.append("coin=").append(coin);
            hasParams = true;
        }
        if (txId != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("txId=").append(txId);
            hasParams = true;
        }
        if (status != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("status=").append(status);
            hasParams = true;
        }
        if (statuses != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("statuses=").append(statuses);
            hasParams = true;
        }
        if (startTime != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("startTime=").append(startTime);
            hasParams = true;
        }
        if (endTime != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("endTime=").append(endTime);
            hasParams = true;
        }
        if (offset != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("offset=").append(offset);
            hasParams = true;
        }
        if (limit != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("limit=").append(limit);
        }
        
        return httpClient.get(DEPOSIT_HISTORY_ENDPOINT, queryBuilder.toString(), new TypeReference<List<DepositRecordVo>>() {});
    }
    
    /**
     * Get withdraw history
     * 
     * @param request Withdraw history query request
     * @return List of withdraw records
     * @throws CoinsApiException if the API call fails
     */
    public List<WithdrawRecordVo> getWithdrawHistory(WithdrawHistoryQueryRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request.getCoin() != null) {
            queryBuilder.append("coin=").append(request.getCoin());
            hasParams = true;
        }
        if (request.getWithdrawOrderId() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("withdrawOrderId=").append(request.getWithdrawOrderId());
            hasParams = true;
        }
        if (request.getStatus() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("status=").append(request.getStatus());
            hasParams = true;
        }
        if (request.getStartTime() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("startTime=").append(request.getStartTime());
            hasParams = true;
        }
        if (request.getEndTime() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("endTime=").append(request.getEndTime());
            hasParams = true;
        }
        if (request.getOffset() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("offset=").append(request.getOffset());
            hasParams = true;
        }
        if (request.getLimit() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("limit=").append(request.getLimit());
        }
        
        return httpClient.get(WITHDRAW_HISTORY_ENDPOINT, queryBuilder.toString(), new TypeReference<List<WithdrawRecordVo>>() {});
    }
    
    /**
     * Apply for withdrawal
     * 
     * @param request Withdraw apply request
     * @return Withdrawal result
     * @throws CoinsApiException if the API call fails
     */
    public WithdrawApplyVo applyWithdraw(WithdrawApplyRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        // Use postWithBody to send JSON request body
        return httpClient.postWithBody(WITHDRAW_APPLY_ENDPOINT, request, new TypeReference<WithdrawApplyVo>() {});
    }
    
    /**
     * Get transaction history
     * 
     * @param request Transaction history request
     * @return Transaction history
     * @throws CoinsApiException if the API call fails
     */
    public GetTransactionHistoryResponse getTransactionHistory(GetTransactionHistoryRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request.getTokenId() != null) {
            queryBuilder.append("tokenId=").append(request.getTokenId());
            hasParams = true;
        }
        if (request.getStartTime() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("startTime=").append(request.getStartTime());
            hasParams = true;
        }
        if (request.getEndTime() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("endTime=").append(request.getEndTime());
            hasParams = true;
        }
        if (request.getSubUserId() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("subUserId=").append(request.getSubUserId());
            hasParams = true;
        }
        if (request.getPageNum() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("pageNum=").append(request.getPageNum());
            hasParams = true;
        }
        if (request.getPageSize() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("pageSize=").append(request.getPageSize());
        }
        
        return httpClient.get(TRANSACTION_HISTORY_ENDPOINT, queryBuilder.toString(), new TypeReference<GetTransactionHistoryResponse>() {});
    }
    
    /**
     * Get address whitelist
     * 
     * @param request Address whitelist query request
     * @return List of whitelisted addresses
     * @throws CoinsApiException if the API call fails
     */
    public List<AddressWhitelistVo> getAddressWhitelist(WithdrawWhitelistQueryRequest request) throws CoinsApiException {
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request.getCoin() != null) {
            queryBuilder.append("coin=").append(request.getCoin());
            hasParams = true;
        }
        if (request.getNetwork() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("network=").append(request.getNetwork());
            hasParams = true;
        }
        if (request.getAddress() != null) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("address=").append(request.getAddress());
        }
        
        return httpClient.get(ADDRESS_WHITELIST_ENDPOINT, queryBuilder.toString(), new TypeReference<List<AddressWhitelistVo>>() {});
    }
}
