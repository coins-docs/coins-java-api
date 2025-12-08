package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author sandy
 * @date 2023-04-03
 * @desc FiatCashOutRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatCashOutRequest {

    /**
     * 商户用户id
     */
    private String userId;

    /**
     * 法币本地id (可以为空, 当通过open-api调用时,可传)
     */
    @Length(min = 10, max = 60, message = "internalOrderId between 10 and 60!")
    private String internalOrderId;

    /**
     * 币种
     */
    @NotBlank(message = "currency is not allowed null")
    private String currency;

    /**
     * 提现金额
     */
    @NotBlank(message = "amount is not allowed null")
    private String amount;

    /**
     * 渠道 (instapay/pesonet)
     */
    @NotBlank(message = "channel name is not allowed null")
    private String channelName;

    /**
     * 次级渠道 (gcash)
     */
    @NotBlank(message = "channel subject is not allowed null")
    private String channelSubject;

    /**
     * 额外扩展信息
     */
//    private Map<String, String> extendInfo = new HashMap<>();

//    @StringValidation(forbidChars = {"<", ">", "\"", "'", "%", ";", "(", ")", "&", "+"})
    private Map<String,String> extendInfo;
}
