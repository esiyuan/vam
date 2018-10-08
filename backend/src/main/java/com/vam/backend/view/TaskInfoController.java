package com.vam.backend.view;

import com.github.pagehelper.Page;
import com.vam.backend.dto.TaskInfoParamDto;
import com.vam.backend.dto.TaskTypeParamDto;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.util.BeanMapper;
import com.vam.task.viewfacade.TaskInfoViewService;
import com.vam.task.viewfacade.VamPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 任务明细管理
 */
@Controller
@RequestMapping(path = "/task")
@Slf4j
public class TaskInfoController {
    @Autowired
    private TaskInfoViewService taskInfoViewService;
    private static String TYPE_INFO_LIST_PAGE = "taskInfoList";

    @RequestMapping(value = "show.htm")
    public String show(ModelMap modelMap) {
        log.info("show");
        VamPage vamPage = new VamPage();
        vamPage.setStart(1);
        vamPage.setPageSize(10);
        return search(null, vamPage, modelMap);
    }

    @RequestMapping(value = "/search.htm")
    public String search(TaskInfoParamDto taskInfoParamDto, VamPage vamPage, ModelMap modelMap) {
        log.info("search {} - {}", taskInfoParamDto, vamPage);
        if (taskInfoParamDto == null) {
            taskInfoParamDto = new TaskInfoParamDto();
        }
        Page<TaskInfoDmo> page = taskInfoViewService.search(taskInfoParamDto.toDmoMap(), vamPage);
        modelMap.put("taskList", page);
        handlerModel(modelMap, taskInfoParamDto, VamPage.from(page));
        return TYPE_INFO_LIST_PAGE;
    }

    private void handlerModel(ModelMap modelMap, TaskInfoParamDto taskInfoParamDto, VamPage vamPage) {
        modelMap.put("param", taskInfoParamDto);
        modelMap.put("page", vamPage);
    }
}



