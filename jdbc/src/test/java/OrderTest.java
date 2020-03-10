import com.lv.demo.sharding.App;
import com.lv.demo.sharding.model.Order;
import com.lv.demo.sharding.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void searchTest() {
        List<Order> orders = orderService.listOrder(1);
        System.out.println(orders == null ? "null":orders.size());
    }

    @Test
    public void searchOneTest() throws InterruptedException {
        Order order = orderService.getOrder(0);
        Thread.sleep(1000);
        Order order2 = orderService.getOrder(2);
        Thread.sleep(1000);
        Order order1 = orderService.getOrder(28);
        System.out.println(order1.getOrderId());
    }
}
