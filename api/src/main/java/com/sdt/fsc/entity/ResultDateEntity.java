package com.sdt.fsc.entity;

import com.sdt.fsc.entity.response.Aggregations;
import com.sdt.fsc.entity.response.Hits;
import com.sdt.fsc.entity.response._shards;

import java.io.Serializable;

/**
 *  Search 返回实体
 * @author wangxinhao
 */
public class ResultDateEntity implements Serializable {
    private static final long serialVersionUID = 74684252864610779L;
    private Integer took;
    private boolean time_out;
    private _shards _shards;
    private Hits hits;
    private Aggregations aggregations;

    public void setTook(Integer took) {
        this.took = took;
    }

    public Integer getTook() {
        return took;
    }

    public void setTime_out(boolean time_out) {
        this.time_out = time_out;
    }

    public boolean getTime_out() {
        return time_out;
    }

    public void set_shards(_shards _shards) {
        this._shards = _shards;
    }

    public _shards get_shards() {
        return _shards;
    }

    public void setHits(Hits hits) {
        this.hits = hits;
    }

    public Hits getHits() {
        return hits;
    }

    public void setAggregations(Aggregations aggregations) {
        this.aggregations = aggregations;
    }

    public Aggregations getAggregations() {
        return aggregations;
    }
}
