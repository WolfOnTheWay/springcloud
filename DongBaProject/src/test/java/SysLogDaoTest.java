import com.cy.Application;
import com.cy.pi.sys.dao.SysLogDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SysLogDaoTest {
    @Autowired
    private SysLogDao sysLogDao;

    @Test
    public void testRowCount(){
        int rowCount = sysLogDao.getRowCount("admin");
        System.out.println(rowCount);
    }
}
