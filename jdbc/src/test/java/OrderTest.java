import com.lv.demo.sharding.App;
import com.lv.demo.sharding.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/***
 *@Title
 *@author shunlv
 *@Date 2020/3/11 1:06 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class OrderTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            orderService.addOrder();
        }

    }
}
