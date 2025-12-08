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

    @Length(max=10)
    private String coin;

    @Length(max=64)
    private String withdrawOrderId;

    @Range(min=0, max=2)
    private Integer status;

    @Min(value=0)
    private Integer offset;

    @Range(min=1, max=1000)
    private Integer limit;

    @Min(value=1)
    private Long startTime;

    @Min(value=1)
    private Long endTime;
}
