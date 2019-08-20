package com.qgstudio.root.dao;

import com.qgstudio.root.utils.EmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Slf4j
public class Selector {
    private static final String BASE_SQL = "SELECT * FROM circle WHERE ";
    private static final String ROUTE_BASE_SQL = "SELECT * FROM route_circle_";

    public String selectCircleByDate(@Param("month") String month, @Param("day") String day, @Param("hour") String hour) {
        StringBuilder sb = new StringBuilder();
        boolean flag = Boolean.FALSE;
        sb.append(BASE_SQL);
        if (!EmptyUtil.isEmpty(month)) {
            sb.append("date_month = #{month} ");
            flag = Boolean.TRUE;
        }
        if (!EmptyUtil.isEmpty(day)) {
            if (flag) {
                sb.append(" and ");
            }
            sb.append("  date_day = #{day} ");
            flag = Boolean.TRUE;
        }
        if (!EmptyUtil.isEmpty(hour)) {
            if (flag) {
                sb.append(" and ");
            }
            sb.append(" date_hour = #{hour} ");
        }
        sb.append(" ORDER BY weight");
        return sb.toString();
    }
    public String selectCircleAll(){
        return "SELECT * FROM circle_all  ORDER BY weight";
    }

    public String selectCircleByMonth(@Param("month") String month){
        return "SELECT * FROM circle_month WHERE date_month=#{month} ORDER BY weight";
    }
    public String selectCircleByDay(@Param("month") String month, @Param("day") String day){
        return "SELECT * FROM circle_day WHERE date_month=#{month} AND date_day=#{day} ORDER BY weight ";

    }

    public String selectRouteCircle(@Param("month") String month, @Param("day") String day, @Param("hour") String hour) {
        StringBuilder sb = new StringBuilder();
        sb.append(ROUTE_BASE_SQL);
        boolean flag = Boolean.FALSE;
        if (EmptyUtil.isEmpty(hour)) {
            log.warn("Route circle Selecting , hour is null!");
            //if is null, default query 0 am
            sb.append("0");
        } else {
            sb.append(hour.trim());
        }
        if (EmptyUtil.isEmpty(month)) {
            log.warn("month is null!");
        } else {
            sb.append("  WHERE date_month=#{month}");
            flag = true;
        }
        if (EmptyUtil.isEmpty(day) && !flag) {
            log.warn("all none!");
        } else if (EmptyUtil.isEmpty(day) && flag) {
        } else if (!EmptyUtil.isEmpty(day) && !flag) {
            sb.append("WHERE date_day=#{day} order by route_weight");
        } else {
            sb.append(" AND date_day=#{day} order by route_weight");
        }
        return sb.toString();
    }
}
