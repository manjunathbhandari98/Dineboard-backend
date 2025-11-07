package com.quodex.dineboard.dto.request;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRCodeRequest {
    private String label;
    private String menuId;
    private String hotelId;
    private String url;
    private byte[] urlBytes;
}
