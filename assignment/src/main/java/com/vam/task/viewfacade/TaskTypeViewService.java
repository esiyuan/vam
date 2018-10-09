package com.vam.task.viewfacade;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vam.task.dao.TaskTypeMapper;
import com.vam.task.dmo.TaskTypeDmo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTypeViewService {
    @Autowired
    private TaskTypeMapper taskTypeMapper;

    public Page<TaskTypeDmo> search(TaskTypeDmo taskTypeDmo, VamPage vamPage) {
        PageHelper.startPage(ObjectUtils.max(vamPage.getStart(), 1),
                ObjectUtils.defaultIfNull(vamPage.getPageSize(), 10));
        Page<TaskTypeDmo> page = taskTypeMapper.search(taskTypeDmo);
        return page;
    }

    public int updateTaskType(TaskTypeDmo taskTypeDmo) {
        return taskTypeMapper.updateByPrimaryKeySelective(taskTypeDmo);
    }

    public int saveTaskType(TaskTypeDmo taskTypeDmo) {
        return taskTypeMapper.insert(taskTypeDmo);
    }

    public TaskTypeDmo searchById(long id) {
        return taskTypeMapper.selectByPrimaryKey(id);
    }

    public int deleteById(long id) {
        return taskTypeMapper.deleteByPrimaryKey(id);
    }

}
