package com.qgstudio.root.service;

import com.qgstudio.root.models.RequestData;
import com.qgstudio.root.models.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author linxu
 * @date 2019/5/25
 */
public interface QueryService {
    /**
     * @param condition 查询条件
     * @return city data
     */
    ResponseData queryCircleData(RequestData condition);

    ResponseData queryAllCircle();

    ResponseData queryCircleByMonth(RequestData requestData);
    ResponseData queryCircleByDay(RequestData requestData);

    ResponseData queryRouteCircleData(RequestData condition);

    void updateCircleMap();

    ResponseData queryCircleFlow(int id);

    ResponseData queryAllRouteCircle();
}
