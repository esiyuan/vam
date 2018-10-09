package com.vam.backend.view;

import com.github.pagehelper.Page;
import com.vam.backend.dto.ExceptionParamDto;
import com.vam.backend.dto.TaskInfoParamDto;
import com.vam.backend.util.ViewConstants;
import com.vam.task.dmo.ExceptionDmo;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.viewfacade.ExceptionViewService;
import com.vam.task.viewfacade.TaskInfoViewService;
import com.vam.task.viewfacade.VamPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(path = "/exception")
@Slf4j
public class ExceptionController {

    @Autowired
    private ExceptionViewService exceptionViewService;
    private static String EXCEPTION_LIST_PAGE = "exception";

    @RequestMapping(value = "show.htm")
    public String show(ModelMap modelMap) {
        log.info("show");
        VamPage vamPage = new VamPage();
        vamPage.setStart(1);
        vamPage.setPageSize(10);
        return search(null, vamPage, modelMap);
    }

    @RequestMapping(value = "/search.htm")
    public String search(ExceptionParamDto exceptionParamDto, VamPage vamPage, ModelMap modelMap) {
        log.info("search {} - {}", exceptionParamDto, vamPage);
        if (exceptionParamDto == null) {
            exceptionParamDto = new ExceptionParamDto();
        }
        Page<ExceptionDmo> page = exceptionViewService.search(exceptionParamDto.toDmoMap(), vamPage);
        modelMap.put("exceptionList", convert2ResultMap(page));
        handlerModel(modelMap, exceptionParamDto, VamPage.from(page));
        return EXCEPTION_LIST_PAGE;
    }

    private void handlerModel(ModelMap modelMap, ExceptionParamDto exceptionParamDto, VamPage vamPage) {
        modelMap.put("param", exceptionParamDto);
        modelMap.put("page", vamPage);
    }


    @RequestMapping(value = "delete.htm", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@Param("id") Long id) {
        log.info("delete 参数 :{}", id);
        exceptionViewService.delete(id);
        return ViewConstants.ASYN_RETURN_OK;
    }

    @RequestMapping(value = "move.htm", method = RequestMethod.POST)
    @ResponseBody
    public String move(@Param("id") Long id) {
        log.info("delete 参数 :{}", id);
        exceptionViewService.moveToTask(id);
        return ViewConstants.ASYN_RETURN_OK;
    }


    private List<Map<String, Object>> convert2ResultMap(Page<ExceptionDmo> exceptionParamDtos) {
        if (CollectionUtils.isEmpty(exceptionParamDtos)) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (ExceptionDmo exceptionDmo : exceptionParamDtos) {
            Map<String, Object> each = new HashMap<String, Object>();
            each.put("id", exceptionDmo.getId());
            each.put("bizKey", exceptionDmo.getBizKey());
            each.put("bizType", exceptionDmo.getBizType());
            try {
                if (exceptionDmo.getCreateTime() != null) {
                    each.put("createTime", DateFormatUtils.format(exceptionDmo.getCreateTime(), ViewConstants.VAM_TIME_FORMAT_STR));
                }
            } catch (Exception e) {
                new RuntimeException(e);
            }
            result.add(each);
        }
        return result;
    }
}
