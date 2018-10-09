package com.vam.task.viewfacade;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vam.task.dao.TaskInfoMapper;
import com.vam.task.dmo.TaskInfoDmo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskInfoViewService {
    @Autowired
    private TaskInfoMapper taskInfoMapper;

    public Page<TaskInfoDmo> search(Map<String, Object> taskInfoMap, VamPage vamPage) {
        PageHelper.startPage(ObjectUtils.max(vamPage.getStart(), 1),
                ObjectUtils.defaultIfNull(vamPage.getPageSize(), 10));
        Page<TaskInfoDmo> page = taskInfoMapper.search(taskInfoMap);
        return page;
    }

    public int delete(long id) {
        return taskInfoMapper.deleteByPrimaryKey(id);
    }
}
