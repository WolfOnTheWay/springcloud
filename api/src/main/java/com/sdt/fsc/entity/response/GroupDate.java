package com.sdt.fsc.entity.response;

import java.io.Serializable;
import java.util.List;

public class GroupDate implements Serializable {
    private static final long serialVersionUID = -4045865370235356824L;
    private List<Buckets> buckets;
    public void setBuckets(List<Buckets> buckets) {
        this.buckets = buckets;
    }
    public List<Buckets> getBuckets() {
        return buckets;
    }
}
