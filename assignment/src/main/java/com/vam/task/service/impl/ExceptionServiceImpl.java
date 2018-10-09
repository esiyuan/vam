package com.vam.task.service.impl;

import com.google.common.base.Preconditions;
import com.vam.task.dao.ExceptionMapper;
import com.vam.task.dao.TaskInfoMapper;
import com.vam.task.dmo.ExceptionDmo;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.service.intf.ExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ExceptionServiceImpl implements ExceptionService {
    @Autowired
    private ExceptionMapper exceptionMapper;
    @Autowired
    private TaskInfoMapper taskInfoMapper;


    @Override
    public void moveToException(TaskInfoDmo taskInfoDmo) {
        Preconditions.checkNotNull(taskInfoDmo);
        Preconditions.checkNotNull(taskInfoDmo.getId());

        ExceptionDmo exceptionDmo = new ExceptionDmo();
        exceptionDmo.setBizKey(taskInfoDmo.getBizKey());
        exceptionDmo.setBizType(taskInfoDmo.getBizType());
        exceptionDmo.setCreateTime(taskInfoDmo.getCreateTime());
        exceptionDmo.setLastUpdateTime(new Date());
        try {
            exceptionMapper.insert(exceptionDmo);
        } catch (DuplicateKeyException e) {
            log.warn("异常表中该任务已存在bizKey = {}, bizType = {}", taskInfoDmo.getBizKey(), taskInfoDmo.getBizType());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() != null && e.getMessage().contains("DuplicateKeyException")) {
                log.warn("异常表中该任务已存在,{},{}", taskInfoDmo.getBizKey(), taskInfoDmo.getBizType());
            } else {
                throw e;
            }
        }
        taskInfoMapper.deleteByPrimaryKey(taskInfoDmo.getId());

    }
}
