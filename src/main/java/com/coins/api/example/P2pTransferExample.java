package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.client.base.P2pTransferClient;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.GetBalancesResponse;
import com.coins.api.model.GetTransfersResponse;
import com.coins.api.model.TransferApiRequest;
import com.coins.api.model.TransferApiVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

/**
 * Example usage of the P2P Transfer API
 */
public class P2pTransferExample {
    private static final Logger log = LoggerFactory.getLogger(P2pTransferExample.class);
    
    public static void main(String[] args) {
        // Configure the API client
        CoinsApiConfig config = CoinsApiConfig.builder()
                                              .apiKey("yjpNCpBFzMDrQKKQUChq4YhsFwnKZiNSE4Awmqn53wUPgziaIw3OerWoxeqgh0hu")
                                              .secretKey("sWCboo8ba1LItH2bLD1AQwGnbM9gsLspJ8AlwsoCPGLMSOi3RJ96AONhl2anSGdt")
                                              .baseUrl("https://api.9001.pl-qa.coinsxyz.me")
                                              .recvWindow(5000)
                                              .build();



        // Create the main API client
        CoinsApiClient client = new CoinsApiClient(config);
        P2pTransferClient p2pClient = client.p2pTransfer();
        
        try {
            // Example 1: Get crypto accounts
            log.info("=== Getting Crypto Accounts ===");
            GetBalancesResponse accounts = p2pClient.getCryptoAccounts("PHP");
            log.info("Crypto accounts: {}", accounts);
            
            // Example 2: Execute P2P transfer
            log.info("=== Executing P2P Transfer ===");

            TransferApiRequest transferRequest = new TransferApiRequest();
            transferRequest.setAccount("USDT");
            transferRequest.setAmount(new BigDecimal("10"));
            transferRequest.setTargetAddress("john.zhang@coins.ph");
            transferRequest.setClientTransferId("my-transfer-" + System.currentTimeMillis());
            transferRequest.setMessage("Test transfer");
            TransferApiVo transfer = p2pClient.executeTransfer(transferRequest);
            log.info("P2P transfer result: {}", transfer);
            
            // Example 3: Query transfer history
            log.info("=== Querying Transfer History ===");
            GetTransfersResponse transferHistory = p2pClient.queryTransfers(
                null,    // clientTransferId
                1,       // page
                10,      // perPage
                null,    // toAddress
                null,    // fromAddress
                null     // recvWindow
            );
            log.info("Transfer history: {}", transferHistory);
            
            // Example 4: Query specific transfer by ID
            if (transferHistory != null && !transferHistory.getTransfers().isEmpty()) {
                log.info("=== Querying Specific Transfer ===");
                GetTransfersResponse specificTransfer = p2pClient.queryTransfers(
                        transferHistory.getTransfers().get(0).getClientTransferId(),  // clientTransferId
                    1,       // page
                    1,       // perPage
                    null,    // toAddress
                    null,    // fromAddress
                    null     // recvWindow
                );
                log.info("Specific transfer: {}", specificTransfer);
            }
            
            // Example 5: Get accounts for different currency
            log.info("=== Getting USD Crypto Accounts ===");
            GetBalancesResponse usdAccounts = p2pClient.getCryptoAccounts("USDT");
            log.info("USD crypto accounts: {}", usdAccounts);
            
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
