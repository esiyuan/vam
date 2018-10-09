package com.vam.task.viewfacade;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vam.task.dao.ExceptionMapper;
import com.vam.task.dao.TaskInfoMapper;
import com.vam.task.dmo.ExceptionDmo;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.dto.TaskInfoDto;
import com.vam.task.facade.TaskInfoFacade;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

@Service
public class ExceptionViewService {

    @Autowired
    private ExceptionMapper exceptionMapper;
    @Autowired
    private TaskInfoFacade taskInfoFacade;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public Page<ExceptionDmo> search(Map<String, Object> exceptionMap, VamPage vamPage) {
        PageHelper.startPage(ObjectUtils.max(vamPage.getStart(), 1),
                ObjectUtils.defaultIfNull(vamPage.getPageSize(), 10));
        Page<ExceptionDmo> page = exceptionMapper.search(exceptionMap);
        return page;
    }

    public int delete(long id) {
        return exceptionMapper.deleteByPrimaryKey(id);
    }

    public ExceptionDmo getById(long id) {
        return exceptionMapper.selectByPrimaryKey(id);
    }

    public int moveToTask(final long id) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                ExceptionDmo exceptionDmo = exceptionMapper.selectByPrimaryKey(id);
                TaskInfoDto taskInfoDto = new TaskInfoDto();
                taskInfoDto.setBizKey(exceptionDmo.getBizKey());
                taskInfoDto.setBizType(exceptionDmo.getBizType());
                int result = taskInfoFacade.insert(taskInfoDto);
                exceptionMapper.deleteByPrimaryKey(id);
                return result;
            }
        });
    }
}
