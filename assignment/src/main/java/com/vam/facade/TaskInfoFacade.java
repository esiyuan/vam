package com.vam.facade;

import com.google.common.base.Preconditions;
import com.vam.dao.TaskInfoMapper;
import com.vam.dao.TaskTypeMapper;
import com.vam.dmo.TaskInfoDmo;
import com.vam.dmo.TaskTypeDmo;
import com.vam.dto.TaskInfoDto;
import com.vam.util.VamConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 客户端使用（插入任务）
 */
@Component
@Slf4j
public class TaskInfoFacade {

    @Autowired
    private TaskInfoMapper taskInfoMapper;
    @Autowired
    private TaskTypeMapper taskTypeMapper;

    public int insert(TaskInfoDto record) {
        log.debug("任务插入：{}", record);
        Preconditions.checkNotNull(record.getBizKey());
        Preconditions.checkNotNull(record.getBizType());
        Date current = new Date();
        TaskTypeDmo taskTypeDmo = taskTypeMapper.selectByBizType(record.getBizType());
        log.debug("任务类型={}", taskTypeDmo);
        TaskInfoDmo taskInfoDmo = new TaskInfoDmo();
        taskInfoDmo.setBizType(record.getBizType());
        taskInfoDmo.setBizKey(record.getBizKey());
        taskInfoDmo.setId(record.getId());
        taskInfoDmo.setLastUpdateTime(current);
        taskInfoDmo.setRunCount(0);
        taskInfoDmo.setTimeoutTime(DateUtils.addMinutes(current, taskTypeDmo.getTimeoutMinutes()));
        taskInfoDmo.setCreateTime(current);
        if (taskTypeDmo.getDelayedRunMinutes() > 0) {
            taskInfoDmo.setNextRunTime(DateUtils.addMinutes(current, taskTypeDmo.getDelayedRunMinutes()));
        } else {
            taskInfoDmo.setNextRunTime(current);
        }
        taskInfoDmo.setStatus(VamConstants.TASK_STATUS_NEW);
        int result = taskInfoMapper.insert(taskInfoDmo);
        log.info("任务{}插入result:{}", record, record);
        return result;
    }
}
