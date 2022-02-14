package org.example;

import org.beans.DefaultCache;
import org.junit.Test;

public class TestBeans extends TestBase{
    @Test
    public void testBeans(){
        //key默认为类名首字母小写
        DefaultCache cache = ctx.getBean("defaultCache", DefaultCache.class);
        System.out.println(cache);
    }

}
