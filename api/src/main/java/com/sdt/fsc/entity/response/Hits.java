package com.sdt.fsc.entity.response;

import java.io.Serializable;
import java.util.List;

public class Hits implements Serializable {
    private static final long serialVersionUID = 7915722040611923307L;
    private long total;
    private int max_score;
    private List<String> hits;
    public void setTotal(long total) {
        this.total = total;
    }
    public long getTotal() {
        return total;
    }

    public void setMax_score(int max_score) {
        this.max_score = max_score;
    }
    public int getMax_score() {
        return max_score;
    }

    public void setHits(List<String> hits) {
        this.hits = hits;
    }
    public List<String> getHits() {
        return hits;
    }
}
