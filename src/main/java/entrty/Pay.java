package entrty;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Data;

import java.util.Random;

@Data
public class Pay {

    private Long id;//订单id

    private Long userId;//用戶id

    private Long orderId;//订单id

    private String payWay;//支付方式

    private int balance;//账号未支付前的余额


    private int balanceEnd;//账号支付后的余额

    public Pay(){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        this.setId(snowflake.nextId());
        Random rand = new Random();
        int randNumber = rand.nextInt(4);
        if(randNumber==0){
            this.setPayWay("支付宝");
        }else if (randNumber==2){
            this.setPayWay("微信");
        }else if (randNumber==3){
            this.setPayWay("银行卡");
        }else if (randNumber==4){
            this.setPayWay("信用卡");
        }else {
            this.setPayWay("花呗");
        }
        this.setBalance(rand.nextInt(10001));
    }

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", payWay='" + payWay + '\'' +
                ", balance=" + balance +
                ", balanceEnd=" + balanceEnd +
                '}';
    }
}
