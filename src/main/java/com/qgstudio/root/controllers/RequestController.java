package com.qgstudio.root.controllers;

import com.qgstudio.root.models.RequestData;
import com.qgstudio.root.models.ResponseData;
import com.qgstudio.root.service.QueryService;
import com.qgstudio.root.service.impl.CircleQueryServiceImpl;
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

    @PostMapping("/circle")
    public ResponseData getCircle(@RequestBody RequestData requestData) {
        return queryService.queryCircleData(requestData);
    }

    @PostMapping("/route")
    public ResponseData getRoute(@RequestBody RequestData requestData) {
        return queryService.queryRouteCircleData(requestData);
    }

    @GetMapping("/update")
    public String updateCacheMap(@RequestParam("auth") String auth) {
        if ("linxu".equals(auth)) {
            queryService.updateCircleMap();
            return "Update OK!";
        } else {
            return "auth fail!";
        }
    }
}
