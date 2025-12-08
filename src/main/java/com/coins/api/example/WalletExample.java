package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.client.base.WalletClient;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;

/**
 * Example usage of the Wallet API
 */
public class WalletExample {
    private static final Logger log = LoggerFactory.getLogger(WalletExample.class);
    
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
        WalletClient walletClient = client.wallet();
        
        try {
            // Example 1: Get account information
            log.info("=== Getting Account Information ===");
            AccountInfoResponse account = walletClient.getAccount(50000L);
            log.info("Account info: {}", account);
            
            // Example 2: Get all wallet configurations
            log.info("=== Getting All Wallet Configurations ===");
            List<WalletAssetConfigVo>  configs = walletClient.getAllConfigs();
            log.info("Wallet configs: {}", configs);
            
            // Example 3: Get deposit address
            log.info("=== Getting Deposit Address ===");

            DepositAddress depositAddress = walletClient.getDepositAddress(DepositAddressApiRequest.builder().coin("BTC").network("BTC").build());
            log.info("Deposit address: {}", depositAddress);
            
            // Example 4: Get deposit history
            log.info("=== Getting Deposit History ===");
            List<DepositRecordVo> depositHistory = walletClient.getDepositHistory(
                "BTC", null, null, null, null, null, 0, 10
            );
            log.info("Deposit history: {}", depositHistory);
            
            // Example 5: Get withdraw history
            log.info("=== Getting Withdraw History ===");
            WithdrawHistoryQueryRequest withdrawHistoryRequest = WithdrawHistoryQueryRequest.builder()
                .coin("BTC")
                .offset(0)
                .limit(10)
                .build();
            List<WithdrawRecordVo> withdrawHistory = walletClient.getWithdrawHistory(withdrawHistoryRequest);
            log.info("Withdraw history: {}", withdrawHistory);
            
            // Example 6: Get transaction history
            log.info("=== Getting Transaction History ===");
            GetTransactionHistoryRequest transactionHistoryRequest = GetTransactionHistoryRequest.builder()
                .tokenId("BTC")
                .pageNum(1)
                .pageSize(10)
                .build();
            GetTransactionHistoryResponse transactionHistory = walletClient.getTransactionHistory(transactionHistoryRequest);
            log.info("Transaction history: {}", transactionHistory);
            
            // Example 7: Get address whitelist
            log.info("=== Getting Address Whitelist ===");
            WithdrawWhitelistQueryRequest whitelistRequest = WithdrawWhitelistQueryRequest.builder()
                .coin("BTC")
                .network("BTC")
                .build();
            List<AddressWhitelistVo> whitelist = walletClient.getAddressWhitelist(whitelistRequest);
            log.info("Address whitelist: {}", whitelist);
            
            // Example 8: Apply for withdrawal
            log.info("=== Applying for Withdrawal ===");
            WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder()
                .coin("BTC")
                .network("BTC")
                .address("1MuX8yy4GnKYzJw7ou33dpcmZgM1bGaH9z") // Example Bitcoin address
                .amount(new BigDecimal("0.001"))
                .withdrawOrderId("w" + System.currentTimeMillis())
                .build();
            WithdrawApplyVo withdrawResult = walletClient.applyWithdraw(withdrawApplyRequest);
            log.info("Withdraw result: {}", withdrawResult);
            
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
