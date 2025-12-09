package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawHistoryQueryRequest {

    @Length(max=10, message = "coin length must not exceed 10 characters")
    private String coin;

    @Length(max=64, message = "withdrawOrderId length must not exceed 64 characters")
    private String withdrawOrderId;

    @Range(min=0, max=2, message = "status must be between 0 and 2")
    private Integer status;

    @Min(value=0, message = "offset must be greater than or equal to 0")
    private Integer offset;

    @Range(min=1, max=1000, message = "limit must be between 1 and 1000")
    private Integer limit;

    @Min(value=1, message = "startTime must be greater than 0")
    private Long startTime;

    @Min(value=1, message = "endTime must be greater than 0")
    private Long endTime;
}
