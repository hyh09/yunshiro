package com.yunhuihu.redis.redisdao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunhuihu.redis.bean.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2020/9/24.
 */
@Repository
public class UserDaoSvcImpI<K,T> {

    @Autowired
    @Qualifier(value = "RedisTemplate01")
    private  RedisTemplate<String, String> redisTemplate;


    public <T>  void add(String key, Long time, T T){
        Gson  gson =new Gson();
        //gson.toJson(T);
        redisTemplate.opsForValue().set(key,gson.toJson(T),time, TimeUnit.MINUTES);
    }


    /**
     * 由key获取数据
     * @param key
     * @return
     */
    public List<T> getList(String key){
        Gson  gson = new Gson();
        List<T> tList =  null;
        String  strlist= redisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(strlist)){
            return  tList;
        }
        tList = gson.fromJson(strlist,new TypeToken<List<T>>(){}.getType());
        return  tList;
    }


    /**
     * 删除数据
     */
    public  Boolean  deletebyKey(String key){
      return  redisTemplate.opsForValue().getOperations().delete(key);
    }

}
