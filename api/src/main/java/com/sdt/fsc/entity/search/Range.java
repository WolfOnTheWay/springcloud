package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
@Data
public class Range implements Serializable {

    private static final long serialVersionUID = -5562570572905066130L;
    private Timestamps timestamps;

}
