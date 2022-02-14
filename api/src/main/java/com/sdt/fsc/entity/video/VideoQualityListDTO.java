package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoQualityListDTO {
    private Integer total;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPage;
    private VideoQualityDTO[] list;
}
