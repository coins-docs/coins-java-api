# Coins Java API SDK

A comprehensive Java SDK for the Coins API, providing easy access to cryptocurrency trading, wallet management, fiat operations, and payment services.

## 📋 API Modules Overview

The SDK provides complete implementations for major Coins API modules:

### 1. Spot Trading
- **My Trades** (`myTrades`) - Query user's trading history
- **Trade Fee** (`tradeFee`) - Get trading fee information
- **Test Order** (`order_test`) - Test order creation (no actual execution)
- **New Order** (`order_new`) - Create new trading orders
- **Order Details** (`order_detail`) - Query specific order details
- **Open Orders** (`openOrders`) - Get current unfilled orders
- **Cancel Order** (`order_cancel`) - Cancel specific orders
- **Cancel All Orders** (`order_cancelAll`) - Cancel all open orders
- **Order History** (`order_history`) - Query historical orders
- **Cancel Replace Order** (`order_cancelReplace`) - Cancel and replace orders in a single atomic operation

### 2. Convert
- **Get Supported Trading Pairs** (`get-supported-trading-pairs`) - Query supported conversion pairs
- **Get Quote** (`get-quote`) - Get conversion quotes
- **Accept Quote** (`accept-quote`) - Accept quotes and execute conversion
- **Query Order History** (`query-order-history`) - Query conversion order history

### 3. Fiat
- **Support Channel** (`support-channel`) - Query supported fiat channels
- **Details** (`details`) - Get fiat order details
- **History** (`history`) - Query fiat transaction history (V1)
- **Cash Out** (`cash_out`) - Execute fiat withdrawal operations
- **Generate QR Code** (`generate_qr_code`) - Generate payment QR codes
- **Generate Static QR Code** (`generate_static_qr_code`) - Generate static QR codes
- **Cancel QR Code** (`cancel_qr_code`) - Cancel existing QR codes
- **Update QR Code** (`update_qr_code`) - Update QR code status
- **Get QR Code** (`get_qr_code`) - Retrieve QR code information
- **Get QR Code Static List** (`get_qr_code_static_list`) - Get static QR code list

### 4. P2P Transfer
- **Crypto Accounts** (`crypto_accounts`) - Get cryptocurrency account information
- **P2P Transfer** (`p2p_transfer`) - Execute peer-to-peer transfers
- **Query Transfer** (`query_transfer`) - Query transfer records

### 5. Invoice Payment
- **Payment Request** (`payment_request`) - Create new payment requests
- **Get Payment Request** (`get_payment_request`) - Query payment request information
- **Cancel Payment Request** (`cancel_payment_request`) - Cancel payment requests
- **Payment Request Reminder** (`payment_request_reminder`) - Send payment reminders

### 6. Wallet
- **Account** (`account`) - Get account information
- **Config Get All** (`config_getall`) - Get all wallet configurations
- **Deposit Address** (`deposit_address`) - Get deposit addresses
- **Deposit History** (`deposit_history`) - Query deposit history
- **Withdraw History** (`withdraw_history`) - Query withdrawal history
- **Withdraw Apply** (`withdraw_apply`) - Apply for withdrawals
- **Transaction History** (`transaction_history`) - Query transaction history
- **Address Whitelist** (`address_withlist`) - Get withdrawal address whitelist

## Requirements

- Java 11 or higher
- Maven 3.6 or higher

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.coins.api</groupId>
    <artifactId>coins-java-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

Add the following to your `build.gradle`:

```gradle
implementation 'com.coins.api:coins-java-api:1.0.0'
```

## Quick Start

### 1. Configuration

First, configure the API client with your credentials:

```java
import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;

CoinsApiConfig config = CoinsApiConfig.builder()
    .apiKey("your-api-key")
    .secretKey("your-secret-key")
    .baseUrl("https://api.pro.coins.ph")  // Use appropriate base URL
    .recvWindow(5000)  // Optional: request timeout window in milliseconds
    .build();

CoinsApiClient client = new CoinsApiClient(config);
```

### 2. Basic Usage Examples

#### Wallet Operations

```java


WalletClient walletClient=client.wallet();

// Get account information
        AccountInfoResponse account=walletClient.getAccount(5000L);
        System.out.println("Account: "+account);

// Get deposit address
        DepositAddress depositAddress=walletClient.getDepositAddress(
        DepositAddressApiRequest.builder()
        .coin("BTC")
        .network("BTC")
        .build()
        );

// Get transaction history
        GetTransactionHistoryResponse history=walletClient.getTransactionHistory(
        GetTransactionHistoryRequest.builder()
        .tokenId("BTC")
        .pageNum(1)
        .pageSize(10)
        .build()
        );
```

#### Currency Conversion

```java


ConvertClient convertClient=client.convert();

// Get supported trading pairs
        List<SupportedTradingPair> pairs=convertClient.getSupportedTradingPairs(
        GetSupportedTradingPairsRequest.builder()
        .type("CONVERT")
        .build()
        );

// Get conversion quote
        ConvertQuote quote=convertClient.getQuote(
        GetQuoteRequest.builder()
        .sourceCurrency("USDT")
        .targetCurrency("PHP")
        .sourceAmount("100")
        .build()
        );

// Accept quote and execute conversion
        AcceptQuoteResponse result=convertClient.acceptQuote(
        AcceptQuoteRequest.builder()
        .quoteId(quote.getQuoteId())
        .build()
        );
```

#### Spot Trading

```java


SpotTradingClient spotClient=client.spotTrading();

// Place a new order
        NewOrderResponse order=spotClient.newOrder(
        NewOrderRequest.builder()
        .symbol("BTCUSDT")
        .side("BUY")
        .type("LIMIT")
        .quantity("0.001")
        .price("50000")
        .build()
        );

// Get order history
        List<OrderResponse> orders=spotClient.getHistoryOrders(
        HistoryOrdersRequest.builder()
        .symbol("BTCUSDT")
        .build()
        );
```

#### Fiat Operations

```java


FiatClient fiatClient=client.fiat();

// Get supported fiat channels
        List<FiatChannelConfigResponse> channels=fiatClient.getSupportedChannels(
        FiatChannelConfigRequest.builder().build()
        );

// Generate QR code for payments
        OpenApiQrCodeGenerateResponse qrCode=fiatClient.generateQrCode(
        OpenApiQrCodeGenerateRequest.builder()
        .amount("100")
        .currency("PHP")
        .build()
        );
```

## API Clients

The SDK provides several specialized clients:

| Client | Description |
|--------|-------------|
| `WalletClient` | Account info, deposits, withdrawals, transaction history |
| `SpotTradingClient` | Spot trading operations and order management |
| `ConvertClient` | Currency conversion services |
| `FiatClient` | Fiat currency operations and QR code management |
| `P2pTransferClient` | Peer-to-peer transfers |
| `InvoicePaymentClient` | Payment request management |

## Error Handling

The SDK uses `CoinsApiException` for API-related errors:

```java
try {
    AccountInfoResponse account = walletClient.getAccount(5000L);
} catch (CoinsApiException e) {
    System.err.println("API Error: " + e.getMessage());
    System.err.println("Error Code: " + e.getCode());
} catch (Exception e) {
    System.err.println("Unexpected error: " + e.getMessage());
}
```

## Authentication

The SDK automatically handles HMAC-SHA256 signature generation for authenticated requests. You only need to provide your API key and secret key in the configuration.

### Security Best Practices

- Store your API credentials securely (environment variables, secure configuration files)
- Never commit API keys to version control
- Use appropriate `recvWindow` values to prevent replay attacks
- Regularly rotate your API keys

## Examples

Complete examples are available in the `src/main/java/com/coins/api/example/` directory:

- `WalletExample.java` - Wallet operations
- `ConvertExample.java` - Currency conversion
- `SpotTradingExample.java` - Spot trading
- `FiatApiExample.java` - Fiat operations
- `PaymentExample.java` - Payment requests
- `P2pTransferExample.java` - P2P transfers

## Configuration Options

| Option | Description | Default |
|--------|-------------|---------|
| `baseUrl` | API base URL | Required |
| `apiKey` | Your API key | Required |
| `secretKey` | Your secret key | Required |
| `recvWindow` | Request timeout window (ms) | 5000 |

## Dependencies

The SDK uses the following key dependencies:

- **OkHttp 4.11.0** - HTTP client
- **Jackson 2.15.2** - JSON processing
- **SLF4J 2.0.7** - Logging
- **Lombok 1.18.30** - Code generation
- **Hibernate Validator 6.2.5** - Request validation

## Building from Source

```bash
git clone <repository-url>
cd coins-java-api
mvn clean install
```

## Testing

Run the test suite:

```bash
mvn test
```

## API Documentation

For detailed API documentation, please refer to the official Coins API documentation.

## Support

For issues and questions:

1. Check the examples in the `example` package
2. Review the API documentation
3. Create an issue in the project repository

## License

This project is licensed under the terms specified in the project license file.

## 🆘 Support

- **Documentation**: [Coins API Documentation](https://docs.coins.ph)
- **Issues**: [GitHub Issues](https://github.com/coins-ph/coins-java-sdk/issues)
- **Email**: support@coins.ph

## Changelog

### Version 1.0.0
- Initial release
- Support for Wallet, Spot Trading, Convert, Fiat, P2P Transfer, and Payment APIs
- HMAC-SHA256 authentication
- Comprehensive error handling
- Complete example implementations
