package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiUpdateQrCodeRequest {
    
    @NotBlank(message = "requestId is not allowed to be empty.")
    @Size(min = 15, max = 36, message = "requestId  must be within 15~36 characters.")
    private String requestId;
    
    /**
     * 二维码状态
     */
    private String status;
}
