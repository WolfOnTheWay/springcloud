package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 排序
 * @author wangxinhao
 */
@Accessors(chain = true)
@Data
public class Sort implements Serializable {
    private static final long serialVersionUID = -8911351630569003169L;
    private Timestamps timestamps;

}
