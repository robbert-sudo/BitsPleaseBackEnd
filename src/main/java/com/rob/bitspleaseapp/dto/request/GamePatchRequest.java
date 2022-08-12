package com.rob.bitspleaseapp.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GamePatchRequest {
    private String name;
    private String system;
    private String developer;
    private BigDecimal price;
}
