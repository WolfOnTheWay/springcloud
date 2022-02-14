package com.sdt.fsc.entity.kbm;

import com.sdt.fsc.entity.response.ContentStrTime;

import java.io.Serializable;
import java.util.List;

public class ADThresholdEntity implements Serializable {
    private static final long serialVersionUID = 614104416720804575L;
    private List<ContentStrTime> contentStrTime;
    private Integer upperLimit;
    private Integer lowerLimit;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public List<ContentStrTime> getContentStrTime() {
        return contentStrTime;
    }

    public void setContentStrTime(List<ContentStrTime> contentStrTime) {
        this.contentStrTime = contentStrTime;
    }
}
