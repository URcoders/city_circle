package com.qgstudio.root.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qgstudio.root.utils.EmptyUtil;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Data
@Service
public class CityCircle extends AbstractCircle<List<Point>> {
    @JsonIgnore
    private String month;
    @JsonIgnore
    private String day;
    @JsonIgnore
    private String hour;
    private List<Point> pointList;
    private static final String SPLIT_POINT = "\\|";
    private static final String SPLIT_LAT_LON = "\\:";

    /**
     * transfer 112.8976:23.456|112.8976:23.456|112.8976:23.456
     *
     * @return list
     */
    @Override
    public List<Point> transfer() {
        List<Point> list = new LinkedList<>();
        if (!EmptyUtil.isEmpty(this.latAndLonCircle)) {
            String[] points = latAndLonCircle.split(SPLIT_POINT);
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
        pointList = list;
        return list;
    }

    @Override
    public String toString() {
        return "CityCircle{" +
                "month='" + month + '\'' +
                "day='" + day + '\'' +
                "hour='" + hour + '\'' +
                "weight='" + weight + '\'' +
                "id='" + id + '\'' +
                "msg='" + msg + '\'' +
                "lon_lat_circle='" + latAndLonCircle + '\'' +
                '}';
    }

  /*  public static void main(String[] args) {
        String a = "112.8976:23.456|112.8976:22.456|112.8976:23.434";
        CityCircle circle = new CityCircle();
        circle.setLatAndLonCircle(a);
        for (Point p : circle.transfer()
                ) {
            System.out.println(p);
        }
    }*/
}
