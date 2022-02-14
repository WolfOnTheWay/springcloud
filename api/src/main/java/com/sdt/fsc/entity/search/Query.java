package com.sdt.fsc.entity.search;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Query implements Serializable {
    private static final long serialVersionUID = -6886235162695686434L;
    private Bool bool;
}
