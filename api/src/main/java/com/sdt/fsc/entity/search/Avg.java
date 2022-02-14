package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
@Data
public class Avg implements Serializable {
    private static final long serialVersionUID = 1571574185841507192L;
    private String field;

}
