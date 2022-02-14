package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPo {
    private String channel;
    private String startTime;
    private String stopTime;
}
