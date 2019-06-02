package com.qgstudio.root.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Data
@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
    private String msg;
    private Integer status;
    private List<CityCircle> circles;
    private List<RouteCityCircle> routeList;
}
