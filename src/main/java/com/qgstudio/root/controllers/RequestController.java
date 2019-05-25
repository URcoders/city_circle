package com.qgstudio.root.controllers;

import com.qgstudio.root.models.RequestData;
import com.qgstudio.root.models.ResponseData;
import com.qgstudio.root.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/5/25
 */
@RestController
@RequestMapping("flyIntoSky")
@CrossOrigin
public class RequestController {
    @Autowired
    private QueryService queryService;
    @GetMapping("/circle")
    public ResponseData getCircle(@RequestBody RequestData requestData) {
        return queryService.queryCircleData(requestData);
    }

}
