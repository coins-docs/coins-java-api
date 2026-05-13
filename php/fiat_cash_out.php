<?php
/**
 * PHP implementation of com.coins.api.client.base.FiatClient#cashOut
 *
 * Faithfully replicates the Java SDK's HttpClient.postWithBody() flow:
 *   1. Convert request to sorted key=value pairs (TreeMap ordering)
 *   2. Append timestamp & recvWindow
 *   3. Build query-string for signature: key1=val1&key2=val2 (sorted, no URL-encoding)
 *   4. signature = HMAC-SHA256(secretKey, queryString)
 *   5. POST as application/x-www-form-urlencoded with X-COINS-APIKEY header
 */

// ============================================================================
// Configuration – credentials from FiatApiExample.java
// ============================================================================
$config = [
    'apiKey'     => 'sN7I8WSudxSNlVJztTaZf7nfIzhdbeitZ8EWlOnn9qUJm0RvznW03DmB8IM3VTik',
    'secretKey'  => 'J6ZyMS8RKPFLSm4DQ488UwzuSP26agbYax1TphnrB13QoxKUiEMOfduWXpPJYHYw',
    'baseUrl'    => 'https://api.9001.pl-qa.coinsxyz.me',
    'recvWindow' => 5000,
];

// ============================================================================
// Cash-out request parameters – matches FiatApiExample.java
// ============================================================================
$cashOutParams = [
    'internalOrderId' => 'cashout-' . intval(microtime(true) * 1000),
    'currency'        => 'PHP',
    'amount'          => '100.00',
    'channelName'     => 'SWIFTPAY_PESONET',
    'channelSubject'  => 'gcash',
    'extendInfo'      => [
        'recipientName'          => 'sandy openapitest',
        'recipientAccountNumber' => '123456789',
    ],
];

// ============================================================================
// Helper: Flatten request params to string map (mirrors Jackson convertValue + TreeMap)
// ============================================================================

/**
 * Convert a nested associative array into a flat key→string map.
 * For nested arrays/maps, Java's Map.toString() format is used:
 *   {key1=val1, key2=val2}
 *
 * @param array $params
 * @return array  flat string map
 */
function flattenParams(array $params): array
{
    $result = [];
    foreach ($params as $key => $value) {
        if ($value === null) {
            continue;
        }
        if (is_array($value)) {
            // Mimic Java LinkedHashMap.toString(): {k1=v1, k2=v2}
            $parts = [];
            foreach ($value as $k => $v) {
                $parts[] = "$k=$v";
            }
            $result[$key] = '{' . implode(', ', $parts) . '}';
        } else {
            $result[$key] = (string)$value;
        }
    }
    return $result;
}

/**
 * Build sorted query string (no URL-encoding on values, matching UrlBuilder.buildQueryString()).
 * Uses alphabetical (TreeMap) ordering.
 *
 * @param array $params  flat string map
 * @return string
 */
function buildSortedQueryString(array $params): string
{
    ksort($params);
    $pairs = [];
    foreach ($params as $k => $v) {
        $pairs[] = "$k=$v";
    }
    return implode('&', $pairs);
}

/**
 * Generate HMAC-SHA256 signature (matches SignatureUtils.generateSignature).
 *
 * @param string $data      The query-string data to sign
 * @param string $secretKey The secret key
 * @return string  hex-encoded signature
 */
function generateSignature(string $data, string $secretKey): string
{
    return hash_hmac('sha256', $data, $secretKey);
}

// ============================================================================
// cashOut implementation
// ============================================================================

/**
 * Execute Fiat Cash Out — POST openapi/fiat/v1/cash-out
 *
 * @param array $config
 * @param array $cashOutParams
 * @return array  decoded JSON response
 */
function cashOut(array $config, array $cashOutParams): array
{
    // 1. Validate required fields (@NotBlank in FiatCashOutRequest)
    foreach (['currency', 'amount', 'channelName', 'channelSubject'] as $field) {
        if (empty($cashOutParams[$field])) {
            throw new RuntimeException("Validation failed: $field is not allowed null");
        }
    }

    // 2. Flatten request to string map (like objectMapper.convertValue(request, Map.class))
    $paramMap = flattenParams($cashOutParams);

    // 3. Add timestamp & recvWindow (as HttpClient.postWithBody does)
    $paramMap['timestamp'] = (string)intval(microtime(true) * 1000);
    if ($config['recvWindow'] > 0) {
        $paramMap['recvWindow'] = (string)$config['recvWindow'];
    }

    // 4. Build sorted query string for signature
    $signatureData = buildSortedQueryString($paramMap);
    echo "[DEBUG] Signature data: $signatureData\n\n";

    // 5. Compute signature
    $signature = generateSignature($signatureData, $config['secretKey']);
    $paramMap['signature'] = $signature;

    // 6. Sort again (after adding signature) and build form body
    ksort($paramMap);

    // 7. Build URL
    $url = rtrim($config['baseUrl'], '/') . '/openapi/fiat/v1/cash-out';

    // 8. Build URL-encoded form body
    $formBody = http_build_query($paramMap, '', '&', PHP_QUERY_RFC3986);

    // 9. Execute POST request
    $headers = [
        'Content-Type: application/x-www-form-urlencoded',
        'X-COINS-APIKEY: ' . $config['apiKey'],
    ];

    $ch = curl_init();
    curl_setopt_array($ch, [
        CURLOPT_URL            => $url,
        CURLOPT_POST           => true,
        CURLOPT_POSTFIELDS     => $formBody,
        CURLOPT_HTTPHEADER     => $headers,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_TIMEOUT        => 30,
        CURLOPT_SSL_VERIFYPEER => true,
    ]);

    $responseBody = curl_exec($ch);
    $httpCode     = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    $curlError    = curl_error($ch);
    curl_close($ch);

    if ($responseBody === false) {
        throw new RuntimeException("cURL error: $curlError");
    }

    echo "[DEBUG] HTTP $httpCode\n";
    echo "[DEBUG] Response body: $responseBody\n\n";

    // 10. Parse response
    $decoded = json_decode($responseBody, true);
    if (json_last_error() !== JSON_ERROR_NONE) {
        throw new RuntimeException("Failed to parse JSON (HTTP $httpCode): $responseBody");
    }

    // Check for API-level errors (matches HttpClient.executeRequest logic)
    if (isset($decoded['code'])) {
        $errorCode = (int)$decoded['code'];
        if ($errorCode < 0) {
            $errorMsg = $decoded['msg'] ?? 'Unknown error';
            throw new RuntimeException("API error (code=$errorCode): $errorMsg");
        }
    }
    if (isset($decoded['status'])) {
        $status = (int)$decoded['status'];
        if ($status !== 0 && $httpCode >= 400) {
            $errorMsg = $decoded['error'] ?? 'Unknown error';
            throw new RuntimeException("API error (status=$status, HTTP $httpCode): $errorMsg");
        }
    }

    return $decoded;
}

// ============================================================================
// Main
// ============================================================================
try {
    echo "=== Coins Fiat Cash Out (PHP) ===\n\n";

    $response = cashOut($config, $cashOutParams);

    echo "=== Response ===\n";
    echo json_encode($response, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE) . "\n";

    // Extract FiatCashOutResponse fields
    if (isset($response['data'])) {
        $data = $response['data'];
        echo "\n--- Cash Out Result ---\n";
        if (isset($data['externalOrderId'])) echo "External Order ID: {$data['externalOrderId']}\n";
        if (isset($data['internalOrderId'])) echo "Internal Order ID: {$data['internalOrderId']}\n";
    }
} catch (RuntimeException $e) {
    echo "ERROR: " . $e->getMessage() . "\n";
    exit(1);
}