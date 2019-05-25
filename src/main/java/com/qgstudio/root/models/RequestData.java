package com.qgstudio.root.models;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Data
@Service
public class RequestData {
    private String month;
    private String day;
    private String hour;
}
