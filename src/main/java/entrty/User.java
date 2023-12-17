package entrty;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.sun.corba.se.spi.ior.ObjectId;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

@Data
public class User {

    private Long id;//用户id

    private String userName;//用户名

//    private Order order;//目前假设只有一个订单
//
//    private Pay pay;//订单对应的支付信息

    public User() {
        this.setUserName(getRandomCharacters(6));
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        this.setId(snowflake.nextId());
    }

    //生成随机用户名，数字和字母组成,
    private String getRandomCharacters(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
