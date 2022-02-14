package com.sdt.fsc.entity.alarm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControlSaveCaptureDTO {
    private String code;
    private String value;
}
