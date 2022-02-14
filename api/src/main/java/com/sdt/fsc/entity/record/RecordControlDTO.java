package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-16 18:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordControlDTO {
    private String channel;
    private String username;
}
