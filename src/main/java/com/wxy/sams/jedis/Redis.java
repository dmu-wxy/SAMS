package com.wxy.sams.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {
    private JedisPool jedisPool;
    public Redis(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //连接池最大空闲数
        config.setMaxIdle(300);
        //连接池最大连接数
        config.setMaxTotal(1000);
        //连接最大等待时间 -1表示没有限制
        config.setMaxWaitMillis(30000);
        //在空闲时检查有效性
        config.setTestOnBorrow(true);
        /**
         * config 配置
         * localhost ip
         * 30000 等待时间(ms)
         * meteor 密码
         */
        jedisPool = new JedisPool(config,"smartdog.top",6379,30000,"meteor");
    }
    public void excute(CallWithJedis callWithJedis){
        try(Jedis jedis = jedisPool.getResource()){
            callWithJedis.call(jedis);
        }
    }
}
