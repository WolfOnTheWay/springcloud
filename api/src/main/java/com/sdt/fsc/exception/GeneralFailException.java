package com.sdt.fsc.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/7/15 8:52 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GeneralFailException extends RuntimeException {
    private Integer code;
    private String message;
}
