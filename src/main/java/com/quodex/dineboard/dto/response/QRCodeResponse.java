package com.quodex.dineboard.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRCodeResponse {
    private String id;
    private String label;
    private String url;
    private byte[] urlBytes;
    private MenuResponse menu;
    private HotelResponse hotel;
}
