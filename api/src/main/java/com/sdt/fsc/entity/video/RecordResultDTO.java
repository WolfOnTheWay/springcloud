package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordResultDTO {
    private String indexCode;
    private String date;
    private Integer collectTime;
    private String inspectionDate;
    private String result;
    private VideoClipstimeSegment[] videoClipstimeSegment;
    private Integer intactDuration;
    private Integer planDuration;
}
