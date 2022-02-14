package com.sdt.fsc.entity.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
@Data
public class GroupDate implements Serializable {
    private static final long serialVersionUID = -4563050880050733427L;
    private Date_histogram date_histogram;
    private Aggs aggs;

}
