package com.quodex.dineboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuViewResponse {
    private String id;
    private MenuResponse menu;
    private LocalDateTime createdAt;
}
