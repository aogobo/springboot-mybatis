package com.samchen.gorun.controller;

import com.samchen.gorun.entity.User;
import com.samchen.gorun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }


    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser() {
        ExecutorService service = new ThreadPoolExecutor(5, 10, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        int count = 200000 - 120775;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 120775; i <= 200000; i++) {
            User user = new User();
            String[] usernames = {"柳岩", "苍老师", "Angelababy", "热巴", "周冬雨", "周芷若", "王语嫣"};
            int index = i % 7;
            user.setUsername(usernames[index] + i);
            user.setPassword(UUID.randomUUID().toString().replace("-", ""));


            service.execute(() -> {
               userService.addUser(user);
            });
            latch.countDown();
//            userService.addUser(user);
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