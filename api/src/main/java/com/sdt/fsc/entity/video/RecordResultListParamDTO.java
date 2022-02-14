package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordResultListParamDTO {
    private Integer pageNo;
    private Integer pageSize;
    private String beginTime;
    private String endTime;
    private String[] indexCodes;
}
