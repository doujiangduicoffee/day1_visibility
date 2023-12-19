import entrty.Order;
import entrty.Pay;
import entrty.User;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Visibility {

    private User user = new User();
    private Order order = new Order();
    ;
    private Pay pay = new Pay();

    public static void main(String[] args) throws InterruptedException {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            users.add(new User());
//        }

//        for (User user : users) {
//            Thread order = new Thread(new orderSys(user));
//            Thread pay = new Thread(new paySys(user));
//            order.start();
//            pay.start();
//            System.out.println(user.toString());
//            System.out.println(user.getOrder().toString());
//            System.out.println(user.getPay().toString());
//        }
        Visibility visibility = new Visibility();
        visibility.test();
    }

    public void test() throws InterruptedException {
        Thread orderTh = new Thread(new orderSys());
        Thread payTh = new Thread(new paySys());
        orderTh.start();
        payTh.start();
        Thread.sleep(1000);
        System.out.println(user.toString());
        System.out.println(order.toString());
        System.out.println(pay.toString());
    }

    class orderSys implements Runnable {

//    private User user;

        //    public orderSys(User user){
//        this.user = user;
//    }
        Lock lock = new ReentrantLock();

        @SneakyThrows
        @Override
        public void run() {
            lock.lock();
            order.setCreateTime(new Date());
            order.setUserId(user.getId());
            Random rand = new Random();
            order.setMoney(rand.nextInt(4000) + 50);
            lock.unlock();
            int balance = pay.getBalance();
            lock.lock();
            if (balance > order.getMoney()) {//账户余额大于订单金额
                order.setSign(true);
            } else {
                order.setSign(false);
            }
            order.setEndTime(new Date());
            lock.unlock();
        }
    }


    class paySys implements Runnable {
        Lock lock = new ReentrantLock();

//    private User user;
//
//    public paySys(User user){
//        this.user = user;
//    }

        @SneakyThrows
        @Override
        public void run() {
            lock.lock();
            pay.setUserId(user.getId());
            pay.setOrderId(order.getId());
            lock.unlock();
            if (Objects.isNull(order.getSign())) {
                Thread.sleep(100);
            }
            if (order.getSign()) {
                pay.setBalanceEnd(pay.getBalance() - order.getMoney());
            } else {
                pay.setBalanceEnd(pay.getBalance());
            }
        }
    }
}

