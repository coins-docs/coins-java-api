package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @Description:
 * @Author nick
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(builderMethodName = "webPageResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebPageResponse<T> extends WebResponse<List<T>> {
    private long total;
}
