package com.vam.backend.dto;

import com.vam.backend.util.ViewConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
@Setter
@Getter
@ToString
public class ExceptionParamDto {


    private Long id;
    private String bizKey;
    private String bizType;
    private String createTimeStart;
    private String createTimeEnd;


    public Map<String, Object> toDmoMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", id);
        result.put("bizKey", bizKey);
        result.put("bizType", bizType);
        try {
            if (StringUtils.isNotBlank(createTimeStart)) {
                result.put("createTimeStart", DateUtils.parseDate(createTimeStart, ViewConstants.VAM_TIME_FORMAT_STR));
            }
            if (StringUtils.isNotBlank(createTimeEnd)) {
                result.put("nextRunTimeEnd", DateUtils.parseDate(createTimeEnd, ViewConstants.VAM_TIME_FORMAT_STR));
            }
        } catch (ParseException e) {
            new RuntimeException(e);
        }
        return result;
    }
}
