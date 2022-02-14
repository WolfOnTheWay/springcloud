package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopRecordParamDTO {
    @NotNull(message = "摄像头ID不能为空")
    private String cameraId;
    @NotNull(message = "用户名不能为空")
    private String username;
    private String recordLabeName;
    @NotNull(message = "录像标签标记时间不能为空")
    private Long recordLabeTime;
}
