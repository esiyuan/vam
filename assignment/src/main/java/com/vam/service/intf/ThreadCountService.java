package com.vam.service.intf;

public interface ThreadCountService {

    /**
     * 统计当前任务记录数（标记线程）
     *
     * @param bizType
     * @return
     */
    int countByType(String bizType);

    /**
     * 增加当前任务类型的记录
     *
     * @param bizType
     * @return
     */
    Long add(String bizType);

    /**
     * 删除记录
     *
     * @param threadId
     */
    void delete(Long threadId);

    /**
     * 处理超时的记录
     */
    void processTimeout();
}
