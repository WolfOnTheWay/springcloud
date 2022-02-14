package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-16 18:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordControlVO {
    @NotNull(message = "摄像头ID不能为空")
    private String cameraId;
    @NotNull(message = "用户名不能为空")
    private String username;
}
