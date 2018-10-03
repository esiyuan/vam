package com.vam.backend.view;

import com.github.pagehelper.Page;
import com.vam.backend.dto.TaskTypeParamDto;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.viewfacade.TaskTypeViewService;
import com.vam.task.viewfacade.VamPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/tasktype")
@Slf4j
public class TaskTypeController {

    private static String TYPE_LIST_PAGE = "typelist";

    @Autowired
    private TaskTypeViewService taskTypeViewService;

    @RequestMapping(value = "show")
    public String show(ModelMap modelMap) {
        log.info("show");
        VamPage vamPage = new VamPage();
        vamPage.setStart(1);
        vamPage.setPageSize(10);
        return search(null, vamPage, modelMap);
    }


    @RequestMapping(value = "/search")
    public String search(TaskTypeParamDto taskTypeParamDto, VamPage vamPage, ModelMap modelMap) {
        log.info("{} --- {}", taskTypeParamDto, vamPage);
        TaskTypeDmo taskTypeDmo = new TaskTypeDmo();
        taskTypeDmo.setMaxRunTimes(10);
        Page<TaskTypeDmo> page = taskTypeViewService.search(taskTypeDmo, vamPage);
        modelMap.put("typeList", page);
        handlerModel(modelMap, taskTypeParamDto, VamPage.from(page));
        return TYPE_LIST_PAGE;
    }

    @RequestMapping(value = "update")
    public String typeUpdate(@Param("id") Integer id) {
        log.info("id = {}", id);
        return "typeupdate";
    }

    @RequestMapping(value = "typeSave", method = RequestMethod.POST)
    public String typeSave(TaskTypeParamDto typeParamDto) {
        log.info("请求typeSave参数:{}", typeParamDto);

        return "redirect:search";
    }


    private void handlerModel(ModelMap modelMap, TaskTypeParamDto taskTypeParamDto, VamPage vamPage) {
        modelMap.put("param", taskTypeParamDto);
        modelMap.put("page", vamPage);
    }


}
