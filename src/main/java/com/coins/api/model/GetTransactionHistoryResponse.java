package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionHistoryResponse {

    private List<TransactionHistoryVo> transactions;

    private PageResponse meta;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse {

        @JsonProperty("has_next")
        private Boolean hasNext;

        @JsonProperty("next_page")
        private int nextPage;

        @JsonProperty("previous_page")
        private int previousPage;
    }
}
