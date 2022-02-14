package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@Accessors(chain = true)
@Data
public class Bool implements Serializable {

    private static final long serialVersionUID = 4142642352893288914L;
    private List<Must> must;
}
