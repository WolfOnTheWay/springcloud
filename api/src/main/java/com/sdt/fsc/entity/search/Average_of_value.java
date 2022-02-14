package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
@Data
public class Average_of_value implements Serializable {
    private static final long serialVersionUID = 6752924117904953514L;
    private Avg avg;

}
