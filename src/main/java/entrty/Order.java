package entrty;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Data;

import java.util.Date;
import java.util.Random;

@Data
public class Order {

    private Long id;//订单id

    private Long userId;//用戶id

    private int money;//订单金额

    private Date createTime;//订单创建时间

    private Date endTime;//订单结束时间


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", sign=" + sign +
                '}';
    }

    private Boolean sign;//订单是否成功创建


    public Order(){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        this.setId(snowflake.nextId());

    }

}
