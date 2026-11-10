package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class StaticQrCodeListResponse {
    
    /**
     * 静态二维码列表
     */
    private List<QrCode> qrCodes;
    
    /**
     * 总数量
     */
    private Integer total;
}
