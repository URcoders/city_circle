package com.qgstudio.root.service.impl;

import com.qgstudio.root.constance.Message;
import com.qgstudio.root.constance.Status;
import com.qgstudio.root.dao.Dao;
import com.qgstudio.root.models.CityCircle;
import com.qgstudio.root.models.RequestData;
import com.qgstudio.root.models.ResponseData;
import com.qgstudio.root.models.RouteCityCircle;
import com.qgstudio.root.service.QueryService;
import com.qgstudio.root.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Service
@Cacheable(cacheNames = "city_map")
public class CircleQueryServiceImpl implements QueryService {
    private static final Map<Integer,RouteCityCircle> CIRCLE_MAP=new HashMap<>();
    @Autowired
    private Dao dao;

    @Override
    @Cacheable(key = "#condition.month+#condition.day+#condition.hour", cacheNames = "city_map",unless="#result == null")
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

    @Override
    public ResponseData queryRouteCircleData(RequestData condition) {
        ResponseData responseData = new ResponseData();
        if (EmptyUtil.isEmpty(condition)) {
            //处理空的查询条件
        }
        List<RouteCityCircle> routeCityCircleList ;
        routeCityCircleList = dao.queryRouteCityCircle(condition.getMonth(), condition.getHour(), condition.getDay());
        for (RouteCityCircle c : routeCityCircleList
                ) {
            //处理from
            RouteCityCircle routeCityCircle = CIRCLE_MAP.get(c.getFromIndex());
            if (routeCityCircle != null) {
                c.setFromCentreLon(routeCityCircle.getTempCentreLon());
                c.setFromCentreLat(routeCityCircle.getTempCentreLat());
                //c.setFromLonAndLat(routeCityCircle.getTempLonAndLat());
                c.setFromList(routeCityCircle.getTempPointList());
            }
            //处理to
            RouteCityCircle routeCityCircle1 = CIRCLE_MAP.get(c.getToIndex());
            if (routeCityCircle1!=null){
                c.setToCentreLon(routeCityCircle1.getTempCentreLon());
                c.setToCentreLat(routeCityCircle1.getTempCentreLat());
                //c.setToLonAndLat(routeCityCircle1.getTempLonAndLat());
                c.setToList(routeCityCircle1.getTempPointList());
            }
            /*//处理成功，则进行合并计算；
            c.transfer();*/
        }
        responseData.setMsg(Message.OK.getMsg());
        responseData.setStatus(Status.OK.getStatus());
        responseData.setRouteList(routeCityCircleList);
        return responseData;
    }
    public void updateCircleMap(){
        for (int i = 0; i <150 ; i++) {
            RouteCityCircle circle=dao.selectCentrePointAndLonLat(i);
            if (circle!=null)circle.transferOutLine();
            CIRCLE_MAP.put(i,circle);
        }
    }
}
