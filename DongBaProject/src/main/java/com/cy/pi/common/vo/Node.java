package com.cy.pi.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/16 17:16
 */
@Data
public class Node implements Serializable {

    private static final long serialVersionUID = -8511267851752778748L;
    private Integer id;
    private String name;
    private Integer parentId;
}
