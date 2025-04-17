package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuDTO {
    private Long id;
    private String title;
    private Long hotelId;
    private boolean isPublished;

    public Menu toEntity() {
        return Menu.builder()
                .id(this.id)
                .title(this.title)
                .isPublished(this.isPublished)
                // NOTE: Hotel should be set separately in service/controller
                .build();
    }
}
