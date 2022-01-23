package com.wzz.redis.service;

import com.wzz.gulimall.common.utils.R;

public interface RedisLockService {
    /**
     * 使用redis实现分布式锁
     *
     * @return
     */
    R getDataByMyPublicLock();

    /**
     * 可重入锁，自动有看门狗机制
     *
     * @return
     */
    R getReentrantLock();

    /**
     * 读写锁  在写的过程中，读会阻塞
     * 在读的过程中，写会阻塞
     * 写+写 阻塞
     * 读+读 共享
     * 只要有写必定等待
     *
     * @return
     */
    String getWriteLock();

    /**
     * 读写锁  在写的过程中，读会阻塞
     * 在读的过程中，写会阻塞
     * 写+写 阻塞
     * 读+读 共享
     * 只要有写必定等待
     *
     * @return
     */
    String getReadLock();

    /**
     * 上计数器锁（闭锁）
     *
     * @return
     */
    String getCountDownLatch();

    /**
     * 获取闭锁结果
     *
     * @return
     */
    String getCountDownLatchResult();

    /**
     * 信号量
     *
     * @return
     */
    String getSemaphoreLock();

    String getSemaphoreLockResult();
}
