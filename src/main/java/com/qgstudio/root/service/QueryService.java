package com.qgstudio.root.service;

import com.qgstudio.root.models.RequestData;
import com.qgstudio.root.models.ResponseData;
import org.springframework.stereotype.Service;

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

    ResponseData queryRouteCircleData(RequestData condition);

    void updateCircleMap();

    ResponseData queryCircleFlow(int id);

    ResponseData queryAllRouteCircle();
}
