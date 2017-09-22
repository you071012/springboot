package com.ukar.Lock;

/**
 * Created by jyou on 2017/9/22.
 *
 * 分布式锁接口
 */
public interface DistributLock {

    /**
     * 尝试获取锁
     * @param lock 锁的名称
     * @param timeout 获取锁的超时时间，单位毫秒
     * @param tryInterval 每隔多少时间尝试获取一次锁，单位毫秒
     * @param lockExpireTime 锁的自动过期时间，单位毫秒
     * @return true：获取锁成功，false：获取锁失败
     */
    boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime);


    /**
     * 尝试释放锁，这里只做锁能否正常释放的判断，不做锁释放的动作
     * @param lock 锁的名称
     * @return true：释放锁成功，false:释放锁失败
     */
    boolean tryRealase(Lock lock);

    /**
     * 执行释放锁的动作
     * @param lock 锁的名称
     */
    void realase(Lock lock);
}
