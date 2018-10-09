package com.vam.backend.dto;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.vam.backend.util.ViewConstants;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.util.VamConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class TaskInfoParamDto implements Serializable {

    private Long id;
    private String bizKey;
    private String bizType;
    private String nextRunTimeStart;
    private String nextRunTimeEnd;
    private Integer status;



    public Map<String, Object> toDmoMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", id);
        result.put("bizKey", bizKey);
        result.put("bizType", bizType);
        try {
            if (StringUtils.isNotBlank(nextRunTimeStart)) {
                result.put("nextRunTimeStart", DateUtils.parseDate(nextRunTimeStart, ViewConstants.VAM_TIME_FORMAT_STR));
            }
            if (StringUtils.isNotBlank(nextRunTimeEnd)) {
                result.put("nextRunTimeEnd", DateUtils.parseDate(nextRunTimeStart, ViewConstants.VAM_TIME_FORMAT_STR));
            }
        } catch (ParseException e) {
            new RuntimeException(e);
        }
        result.put("status", status);
        return result;
    }
}
