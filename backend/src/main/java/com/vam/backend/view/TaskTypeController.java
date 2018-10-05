package com.vam.backend.view;

import com.github.pagehelper.Page;
import com.vam.backend.dto.TaskTypeParamDto;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.viewfacade.TaskTypeViewService;
import com.vam.task.viewfacade.VamPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/tasktype")
@Slf4j
public class TaskTypeController {

    private static String TYPE_LIST_PAGE = "typelist";
    private static String TYPE_PAGE = "type";

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

    @RequestMapping(value = "update")
    public String typeUpdate(@Param("id") Integer id, ModelMap modelMap) {
        log.info("id = {}", id);
        modelMap.put("taskType", taskTypeViewService.searchById(id));
        return "typeupdate";
    }


    @RequestMapping(value = "/search")
    public String search(TaskTypeParamDto taskTypeParamDto, VamPage vamPage, ModelMap modelMap) {
        log.info("search {} - {}", taskTypeParamDto, vamPage);
        TaskTypeDmo taskTypeDmo = new TaskTypeDmo();
        if (StringUtils.isNotBlank(taskTypeParamDto.getBizType())) {
            taskTypeDmo.setBizType(taskTypeParamDto.getBizType());
        }
        Page<TaskTypeDmo> page = taskTypeViewService.search(taskTypeDmo, vamPage);
        modelMap.put("typeList", page);
        handlerModel(modelMap, taskTypeParamDto, VamPage.from(page));
        return TYPE_LIST_PAGE;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@Param("id") Long id) {
        log.info("delete 参数 :{}", id);
        taskTypeViewService.deleteById(id);
        return "ok";
    }

    @RequestMapping(value = "new")
    public String showNew(@ModelAttribute("taskType") TaskTypeParamDto typeParamDto,
                          @ModelAttribute("errorMsg") String errorMsg) {
        log.info("showNew param typeParamDto = {} errorMsg = {}", typeParamDto, errorMsg);


        return TYPE_PAGE;
    }

    @RequestMapping(value = "typeSave", method = RequestMethod.POST)
    public String typeSave(TaskTypeParamDto typeParamDto, RedirectAttributes ra) {
        log.info("请求typeSave参数:{}", typeParamDto);
        try {
            if (typeParamDto.getId() != null) {
                taskTypeViewService.updateTaskType(typeParamDto.toTaskTypeDmo());
            } else {
                taskTypeViewService.saveTaskType(typeParamDto.toTaskTypeDmo());
            }
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", e.getMessage());
            ra.addFlashAttribute("taskType", typeParamDto);
            return "redirect:new";
        }
        return "redirect:search";
    }

    @RequestMapping(value = "convertStatus", method = RequestMethod.POST)
    @ResponseBody
    public String convertStatus(TaskTypeParamDto taskTypeParamDto, ModelMap modelMap) {
        log.info("convertStatus 参数 :{}", taskTypeParamDto);
        TaskTypeDmo taskTypeDmo = new TaskTypeDmo();
        taskTypeDmo.setId(taskTypeParamDto.getId());
        taskTypeDmo.setStatus(taskTypeParamDto.getStatus());
        taskTypeViewService.updateTaskType(taskTypeDmo);
        return "ok";
    }

    private void handlerModel(ModelMap modelMap, TaskTypeParamDto taskTypeParamDto, VamPage vamPage) {
        modelMap.put("param", taskTypeParamDto);
        modelMap.put("page", vamPage);
    }


}
