package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoQualityListParamDTO {
    private Integer pageNo;
    private Integer pageSize;
    private String[] indexCodes;
}
