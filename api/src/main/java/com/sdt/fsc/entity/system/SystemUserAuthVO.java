package com.sdt.fsc.entity.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/10/13 16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemUserAuthVO {
    private String userId;
    private String userName;
    private Serializable token;
}
