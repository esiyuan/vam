package com.vam.backend.view;

import com.github.pagehelper.Page;
import com.vam.backend.dto.TaskInfoParamDto;
import com.vam.backend.util.ViewConstants;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.viewfacade.TaskInfoViewService;
import com.vam.task.viewfacade.VamPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

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
        modelMap.put("taskList", convert2ResultMap(page));
        handlerModel(modelMap, taskInfoParamDto, VamPage.from(page));
        return TYPE_INFO_LIST_PAGE;
    }

    private void handlerModel(ModelMap modelMap, TaskInfoParamDto taskInfoParamDto, VamPage vamPage) {
        modelMap.put("param", taskInfoParamDto);
        modelMap.put("page", vamPage);
    }


    @RequestMapping(value = "delete.htm", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@Param("id") Long id) {
        log.info("delete 参数 :{}", id);
        taskInfoViewService.delete(id);
        return ViewConstants.ASYN_RETURN_OK;
    }


    private List<Map<String, Object>> convert2ResultMap(Page<TaskInfoDmo> taskInfoDmos) {
        if (CollectionUtils.isEmpty(taskInfoDmos)) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (TaskInfoDmo taskInfoDmo : taskInfoDmos) {
            Map<String, Object> each = new HashMap<String, Object>();
            each.put("id", taskInfoDmo.getId());
            each.put("bizKey", taskInfoDmo.getBizKey());
            each.put("bizType", taskInfoDmo.getBizType());
            each.put("runCount", taskInfoDmo.getRunCount());
            each.put("status", taskInfoDmo.getStatus());
            try {
                if (taskInfoDmo.getNextRunTime() != null) {
                    each.put("nextRunTime", DateFormatUtils.format(taskInfoDmo.getNextRunTime(), ViewConstants.VAM_TIME_FORMAT_STR));
                }
                if (taskInfoDmo.getCreateTime() != null) {
                    each.put("createTime", DateFormatUtils.format(taskInfoDmo.getCreateTime(), ViewConstants.VAM_TIME_FORMAT_STR));
                }
                if (taskInfoDmo.getTimeoutTime() != null) {
                    each.put("timeoutTime", DateFormatUtils.format(taskInfoDmo.getTimeoutTime(), ViewConstants.VAM_TIME_FORMAT_STR));
                }
                if (taskInfoDmo.getLastUpdateTime() != null) {
                    each.put("lastUpdateTime", DateFormatUtils.format(taskInfoDmo.getLastUpdateTime(), ViewConstants.VAM_TIME_FORMAT_STR));
                }
            } catch (Exception e) {
                new RuntimeException(e);
            }
            result.add(each);
        }
        return result;
    }
}



