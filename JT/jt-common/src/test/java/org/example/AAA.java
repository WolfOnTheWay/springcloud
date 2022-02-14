package org.example;

import com.jt.pojo.Item;
import com.jt.util.ObjectMapperUtil;
import org.junit.Test;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/20 16:22
 */

public class AAA {
    @Test
    public void testJson(){
        Item item = new Item();
        item.setId(1111L);
        item.setImage("112315641");
        String s = ObjectMapperUtil.toJSON(item);
        System.out.println(s);
    }
}
