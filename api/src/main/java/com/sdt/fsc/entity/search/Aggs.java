package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class Aggs implements Serializable {
    private static final long serialVersionUID = 4724243288594584629L;
    /**
     * 分组数据集
     */
    private GroupDate groupDate;
    private Aggs aggs;
    private Average_of_value average_of_value;

}
