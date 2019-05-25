package com.qgstudio.root.models;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Data
@Service
public class ResponseData {
    private String msg;
    private Integer status;
    private List<CityCircle> circles;

}
