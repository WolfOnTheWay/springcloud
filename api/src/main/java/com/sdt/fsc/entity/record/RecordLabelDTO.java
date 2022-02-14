package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordLabelDTO {
    @NotNull(message = "录像文件ID不能为空")
    private String fileId;
    private String recordLabelName;
    private Long recordLabelTime;
}
