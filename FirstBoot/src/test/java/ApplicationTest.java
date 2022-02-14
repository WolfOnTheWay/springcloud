import com.cy.Application;
import com.cy.pj.common.cache.DefaultCache;
import com.cy.pj.dao.GoodsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

//配置启动类
@RunWith(SpringRunner.class)
//交给spring管理
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private DefaultCache defaultCache;
    @Test
    public void contextLoads(){
        System.out.println("line 18");
        System.out.println(ctx);
        System.out.println(defaultCache);
    }
    @Test
    public void datasourceTest() throws Exception{
        System.out.println("line 33");
        System.out.println(dataSource);
    }

    @Autowired
    private GoodsDao goodsDao;
    @Test
    public void goodsDaoTests()
    {
        int rows = goodsDao.deleteObject(1);
        System.out.println(rows);
    }

    @Test
    public void goodsDelete()
    {
        int rows = goodsDao.deleteObjects(1,2,3);
        System.out.println(rows);
    }


}
