package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
@Data
public class Date_histogram implements Serializable {
    private static final long serialVersionUID = -1660428752988931726L;
    private String field;
    private int min_doc_count;
    private String interval;
    private String offset;
}
