package com.qgstudio.root.controllers;

import com.qgstudio.root.constance.Message;
import com.qgstudio.root.constance.Status;
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

    @GetMapping("/queryflow")
    public ResponseData queryCircleFlow(@RequestParam("id") String id) {
        Integer okId;
        try {
            okId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            ResponseData responseData = new ResponseData();
            responseData.setStatus(Status.OK.getStatus());
            responseData.setMsg(Message.FORMAT_ERROR.getMsg());
            return responseData;
        }
        return queryService.queryCircleFlow(okId);
    }
}
