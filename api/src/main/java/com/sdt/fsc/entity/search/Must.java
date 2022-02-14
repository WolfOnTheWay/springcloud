package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class  Must implements Serializable {

    private static final long serialVersionUID = -8955341459889716457L;
    private Term term;
    private Range range;
}
