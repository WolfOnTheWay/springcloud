package com.sdt.fsc.entity.alarm;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 10:52
 */
public class IdsDTO implements Serializable {
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
