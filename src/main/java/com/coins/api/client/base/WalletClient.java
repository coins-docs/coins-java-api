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
import com.coins.api.util.UrlBuilder;
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
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("recvWindow", recvWindow);
        
        return httpClient.get(ACCOUNT_ENDPOINT, urlBuilder, new TypeReference<AccountInfoResponse>() {});
    }
    
    /**
     * Get all wallet configurations
     * 
     * @return Wallet configurations
     * @throws CoinsApiException if the API call fails
     */
    public List<WalletAssetConfigVo>  getAllConfigs() throws CoinsApiException {
        return httpClient.get(CONFIG_GETALL_ENDPOINT, UrlBuilder.create(""), new TypeReference<List<WalletAssetConfigVo> >() {});
    }
    
    /**
     * Get deposit address
     *
     * @param request
     * @return Deposit address information
     * @throws CoinsApiException if the API call fails
     */
    public DepositAddress getDepositAddress(DepositAddressApiRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);

        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("coin", request.getCoin())
            .addParameter("network", request.getNetwork());
        
        return httpClient.get(DEPOSIT_ADDRESS_ENDPOINT, urlBuilder, new TypeReference<DepositAddress>() {});
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
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("coin", coin)
            .addParameter("txId", txId)
            .addParameter("status", status)
            .addParameter("statuses", statuses)
            .addParameter("startTime", startTime)
            .addParameter("endTime", endTime)
            .addParameter("offset", offset)
            .addParameter("limit", limit);
        
        return httpClient.get(DEPOSIT_HISTORY_ENDPOINT, urlBuilder, new TypeReference<List<DepositRecordVo>>() {});
    }
    
    /**
     * Get withdraw history
     * 
     * @param request Withdraw history query request
     * @return List of withdraw records
     * @throws CoinsApiException if the API call fails
     */
    public List<WithdrawRecordVo> getWithdrawHistory(WithdrawHistoryQueryRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("coin", request.getCoin())
            .addParameter("withdrawOrderId", request.getWithdrawOrderId())
            .addParameter("status", request.getStatus())
            .addParameter("startTime", request.getStartTime())
            .addParameter("endTime", request.getEndTime())
            .addParameter("offset", request.getOffset())
            .addParameter("limit", request.getLimit());
        
        return httpClient.get(WITHDRAW_HISTORY_ENDPOINT, urlBuilder, new TypeReference<List<WithdrawRecordVo>>() {});
    }
    
    /**
     * Apply for withdrawal
     * 
     * @param request Withdraw apply request
     * @return Withdrawal result
     * @throws CoinsApiException if the API call fails
     */
    public WithdrawApplyVo applyWithdraw(WithdrawApplyRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
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
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("tokenId", request.getTokenId())
            .addParameter("startTime", request.getStartTime())
            .addParameter("endTime", request.getEndTime())
            .addParameter("subUserId", request.getSubUserId())
            .addParameter("pageNum", request.getPageNum())
            .addParameter("pageSize", request.getPageSize());
        
        return httpClient.get(TRANSACTION_HISTORY_ENDPOINT, urlBuilder, new TypeReference<GetTransactionHistoryResponse>() {});
    }
    
    /**
     * Get address whitelist
     * 
     * @param request Address whitelist query request
     * @return List of whitelisted addresses
     * @throws CoinsApiException if the API call fails
     */
    public List<AddressWhitelistVo> getAddressWhitelist(WithdrawWhitelistQueryRequest request) throws CoinsApiException {
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("coin", request.getCoin())
            .addParameter("network", request.getNetwork())
            .addParameter("address", request.getAddress());
        
        return httpClient.get(ADDRESS_WHITELIST_ENDPOINT, urlBuilder, new TypeReference<List<AddressWhitelistVo>>() {});
    }
}
