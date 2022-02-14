package com.sdt.fsc.entity;

import com.sdt.fsc.entity.search.Aggs;
import com.sdt.fsc.entity.search.Query;
import com.sdt.fsc.entity.search.Sort;
import com.sdt.fsc.entity.search._source;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ElasticSearch Search 接口实体
 * @author  wangxinhao
 *
 */
@Data
@Accessors(chain = true)
public class SearchEntity implements Serializable {
    private static final long serialVersionUID = 8241689966644195397L;
    private Integer size;
    private Query query;
    private Aggs aggs;
    private List<Sort> sort;
    private Integer from;
}
