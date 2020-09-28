package com.yunhuihu.redis;

import com.yunhuihu.redis.bean.UserDao;
import com.yunhuihu.redis.redisdao.UserDaoSvcImpI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by dell on 2020/9/24.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRedisTemplate {

//    @Autowired
//    private RedisTemplate<String,String>redisTemplate;
//
//    @Test
//    public void set(){
//        redisTemplate.opsForValue().set("myKey","myValue");
//        System.out.println(redisTemplate.opsForValue().get("myKey"));
//    }



    @Autowired
    private UserDaoSvcImpI repository;

    private  String key="userdao";

    @Test
    public  void  Test(){
        UserDao  userDao = new UserDao();
        userDao.setName("孙悟空1");
        userDao.setAge(18);
        userDao.setAddress("花果山");
        userDao.setId(2);
        repository.add(key,900l,userDao);

    }








}
