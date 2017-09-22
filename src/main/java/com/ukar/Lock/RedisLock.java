package com.ukar.Lock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by jyou on 2017/9/22.
 *
 * redis实现分布式锁
 */
@Component
public class RedisLock implements DistributLock{
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        try{
            if(StringUtils.isBlank(lock.getName()) || StringUtils.isBlank(lock.getValue())){
                logger.info("锁的key和value为空");
                return false;
            }

            long startTime = System.currentTimeMillis();//当前时间
            do {
                if(!redisTemplate.hasKey(lock.getName())){//不存在锁
                    ValueOperations<String, String> ops = redisTemplate.opsForValue();
                    /**
                     * 设置锁将在lockExpireTime毫秒，如果未设置成功则后自动超时，TimeUnit.MILLISECONDS 毫秒
                     */
                    ops.set(lock.getName(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                }else{//锁已经存在，处于被占用状态
                    //锁的剩余生存时间
                    Long expireTime = redisTemplate.getExpire(lock.getName(), TimeUnit.MILLISECONDS);
                    if(Math.abs(expireTime - tryInterval) < 10){
                        Thread.sleep(tryInterval + (tryInterval/2));
                    }
                    logger.debug("锁已经存在，请重试");
                }
                if((System.currentTimeMillis() - startTime) > timeout){//尝试获取锁超时，直接跳出循环
                    logger.debug("获取锁超时");
                    return false;
                }
                Thread.sleep(tryInterval);
            }while(redisTemplate.hasKey(lock.getName()));
        }catch (Exception e){
            logger.error("获取锁异常", e);
            return false;
        }
        return false;
    }

    @Override
    public boolean tryRealase(Lock lock) {
        try{
            if(redisTemplate.hasKey(lock.getName())){
                String value = (String)redisTemplate.opsForValue().get(lock.getName());
                if(lock.getValue().equals(value)){//允许释放锁
                    return true;
                }else{
                    logger.debug("key[{}]的值不符合之前设定，可能已被抢占", lock.getName());
                    return false;
                }
            }
            logger.debug("key[{}]不存在", lock.getName());
            return false;
        }catch (Exception e){
            logger.error("释放锁出现异常，释放失败", e);
            return false;
        }

    }

    @Override
    public void realase(Lock lock) {
        if (!StringUtils.isEmpty(lock.getName())) {
            redisTemplate.delete(lock.getName());
        }
    }
}
