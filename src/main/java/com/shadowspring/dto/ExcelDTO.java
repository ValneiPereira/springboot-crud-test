package com.shadowspring.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExcelDTO {
    private String base64;
}
