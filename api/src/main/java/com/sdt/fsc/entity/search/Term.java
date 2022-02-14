package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 排序
 * @author wangxinhao
 */
@Data
@Accessors(chain = true)
public class Term implements Serializable {
    private static final long serialVersionUID = 5026400822698009717L;
    private String base;
    private String key;

}
