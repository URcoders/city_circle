package com.qgstudio.root.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qgstudio.root.utils.EmptyUtil;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/6/2
 */
@Data
@Service
public class RouteCityCircle extends AbstractCircle<List<Point>> {
    @JsonIgnore
    private String month;
    @JsonIgnore
    private String day;
    @JsonIgnore
    private String hour;
    /**
     * 一对区域
     */
    @JsonIgnore
    private List<Point> fromList;
    @JsonIgnore
    private List<Point> toList;
    @JsonIgnore
    private List<Point> tempPointList;
    /**
     * 一对终点；
     */
    private double fromCentreLon;
    private double toCentreLon;
    private double fromCentreLat;
    private double toCentreLat;
    @JsonIgnore
    private double tempCentreLon;
    @JsonIgnore
    private double tempCentreLat;
    @JsonIgnore
    private int fromIndex;
    @JsonIgnore
    private int toIndex;
    @JsonIgnore
    private String fromLonAndLat;
    @JsonIgnore
    private String toLonAndLat;
    @JsonIgnore
    private String tempLonAndLat;

    private static final String SPLIT_POINT = "\\|";
    private static final String SPLIT_LAT_LON = "\\:";

    @Override
    public List<Point> transfer() {
        List<Point> flist = new LinkedList<>();
        List<Point> tlist = new LinkedList<>();
        if (!EmptyUtil.isEmpty(this.fromLonAndLat)) {
            String[] points = fromLonAndLat.split(SPLIT_POINT);
            for (int i = 0; i < points.length; i++) {
                String[] p = points[i].split(SPLIT_LAT_LON);
                //如果该点是正常的，那么则取点；不正常的则丢弃
                if (p.length == 2) {
                    Point point = new Point();
                    point.setLongitude(Double.valueOf(p[0]));
                    point.setLatitude(Double.valueOf(p[1]));
                    flist.add(point);
                }
            }
        }
        if (!EmptyUtil.isEmpty(this.toLonAndLat)) {
            String[] points = toLonAndLat.split(SPLIT_POINT);
            for (int i = 0; i < points.length; i++) {
                String[] p = points[i].split(SPLIT_LAT_LON);
                //如果该点是正常的，那么则取点；不正常的则丢弃
                if (p.length == 2) {
                    Point point = new Point();
                    point.setLongitude(Double.valueOf(p[0]));
                    point.setLatitude(Double.valueOf(p[1]));
                    tlist.add(point);
                }
            }
        }
        fromList = flist;
        toList = tlist;
        return null;
    }
    public List<Point> transferOutLine() {
        List<Point> list = new LinkedList<>();
        if (!EmptyUtil.isEmpty(this.tempLonAndLat)) {
            String[] points = tempLonAndLat.split(SPLIT_POINT);
            for (int i = 0; i < points.length; i++) {
                String[] p = points[i].split(SPLIT_LAT_LON);
                //如果该点是正常的，那么则取点；不正常的则丢弃
                if (p.length == 2) {
                    Point point = new Point();
                    point.setLongitude(Double.valueOf(p[0]));
                    point.setLatitude(Double.valueOf(p[1]));
                    list.add(point);
                }
            }
        }
        tempPointList = list;
        return list;
    }
}
