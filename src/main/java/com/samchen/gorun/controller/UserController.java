package com.samchen.gorun.controller;

import com.samchen.gorun.entity.User;
import com.samchen.gorun.service.UserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping("/addRedis")
    @ResponseBody
    public String addRedis(@RequestParam String key,@RequestParam String value) {
        String result = "failed";
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/stock")
    @ResponseBody
    public String stockDown() {
        String result = "failed";
        RLock rLock = redissonClient.getLock("cus-locker");
        try {
            rLock.lock();
            int stock = Integer.parseInt((String) redisTemplate.opsForValue().get("stock"));
            if(stock > 0){
                stock = stock -1;
                redisTemplate.opsForValue().set("stock",stock + "");
                System.out.println("success" + stock);
                result = "ok " + stock;
            }else{
                System.out.println("without products");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return result;
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser() {
        ExecutorService service = new ThreadPoolExecutor(5, 20, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        int count = 1000200;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 1; i <= 1000200; i++) {
            final int index = i;
            service.execute(() -> {
                userService.updateUser(index);
            });
            latch.countDown();
        }
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
        return "done";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser() {
        ExecutorService service = new ThreadPoolExecutor(5, 20, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        int count = 200;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 1000001; i <= 1000200; i++) {
            User user = new User();
            String[] usernames = {"柳岩", "苍老师", "Angelababy", "热巴", "周冬雨", "周芷若", "王语嫣"};
            int index = i % 7;
            user.setUsername(usernames[index] + i);
            user.setPassword(UUID.randomUUID().toString().replace("-", ""));
            service.execute(() -> {
               userService.addUser(user);
            });
            latch.countDown();
        }

        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
        return "done";
    }

    @RequestMapping("/findUser")
    public List<User> findAllUser(@RequestParam String name) {
        return userService.findByName(name);
    }

    @RequestMapping("/find")
    public List<User> findUser(@RequestBody User user) {
        return userService.findByName(user.getUsername());
    }

}