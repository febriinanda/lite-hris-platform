package com.lite.hris.shift.pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftPatternDTO {
    private String code;
    private String name;
    private int cycleLength;
    private String description;
    private List<ShiftPatternItemDTO> items = new ArrayList<>();
}
