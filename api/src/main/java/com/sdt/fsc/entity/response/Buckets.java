package com.sdt.fsc.entity.response;

import java.io.Serializable;

public class Buckets implements Serializable {
    private static final long serialVersionUID = 8386726441467704366L;
    private long key;
    private int doc_count;
    private Average_of_value average_of_value;

    public void setKey(long key) {
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public void setDoc_count(int doc_count) {
        this.doc_count = doc_count;
    }

    public int getDoc_count() {
        return doc_count;
    }

    public void setAverage_of_value(Average_of_value average_of_value) {
        this.average_of_value = average_of_value;
    }

    public Average_of_value getAverage_of_value() {
        return average_of_value;
    }
}
