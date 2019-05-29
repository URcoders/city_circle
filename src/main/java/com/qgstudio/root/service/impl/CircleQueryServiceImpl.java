package com.qgstudio.root.service.impl;

import com.qgstudio.root.constance.Message;
import com.qgstudio.root.constance.Status;
import com.qgstudio.root.dao.Dao;
import com.qgstudio.root.models.CityCircle;
import com.qgstudio.root.models.RequestData;
import com.qgstudio.root.models.ResponseData;
import com.qgstudio.root.service.QueryService;
import com.qgstudio.root.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Service
@Cacheable(cacheNames = "city_map")
public class CircleQueryServiceImpl implements QueryService {
    @Autowired
    private Dao dao;
    @Override
    @Cacheable(key ="#condition.month+#condition.day+#condition.hour" ,cacheNames = "city_map")
    public ResponseData queryCircleData(RequestData condition) {
        ResponseData responseData = new ResponseData();
        if (EmptyUtil.isEmpty(condition)) {
            //处理空的查询条件
        }
        List<CityCircle> circleList;
        try {
            circleList = dao.queryCityCle(condition.getMonth(), condition.getDay(), condition.getHour());
            //遍历转化，解析出区域经纬度
            for (CityCircle c : circleList) {
                c.transfer();
            }
            //do some transfer
           /* for (CityCircle c : circleList) {
                c.compute();
                dao.updateCentrePoint(c.getCentreLon(),c.getCentreLat(),c.getId());
            }*/
            responseData.setMsg(Message.OK.getMsg());
            responseData.setStatus(Status.OK.getStatus());
            responseData.setCircles(circleList);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMsg(Message.ERROR.getMsg());
            responseData.setStatus(Status.OK.getStatus());
        }
        return responseData;
    }
}
