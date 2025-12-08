package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertOrderHistoryResponse {
    
    private List<ConvertOrder> orders;
    private PageInfo pageInfo;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConvertOrder {
        private String orderId;
        private String quoteId;
        private String sourceCurrency;
        private String targetCurrency;
        private BigDecimal sourceAmount;
        private BigDecimal targetAmount;
        private BigDecimal price;
        private String status;
        private Long createTime;
        private Long updateTime;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageInfo {
        @JsonProperty("current_page")
        private Integer currentPage;
        
        @JsonProperty("page_size")
        private Integer pageSize;
        
        @JsonProperty("total_count")
        private Long totalCount;
        
        @JsonProperty("total_pages")
        private Integer totalPages;
    }
}
