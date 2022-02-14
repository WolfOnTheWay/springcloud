package com.sdt.fsc.entity.search;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author  wangxinhao
 */
@Accessors(chain = true)
@Data
public class Timestamps implements Serializable {
    private static final long serialVersionUID = 3673418517968524213L;
    private String order;
    private Long gte;
    private Long lte;
}
