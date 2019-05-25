package com.qgstudio.root.dao;

import com.qgstudio.root.utils.EmptyUtil;
import org.apache.ibatis.annotations.Param;

/**
 * @author linxu
 * @date 2019/5/25
 */
public class Selector {
    private static final String BASE_SQL = "SELECT * FROM circle WHERE ";

    public String selectCircleByDate(@Param("month") String month, @Param("day") String day, @Param("hour") String hour) {
        StringBuilder sb = new StringBuilder();
        boolean flag=Boolean.FALSE;
        sb.append(BASE_SQL);
        if (!EmptyUtil.isEmpty(month)) {
            sb.append("date_month = #{month} ");
            flag=Boolean.TRUE;
        }
        if (!EmptyUtil.isEmpty(day)) {
            if (flag){
                sb.append(" and ");
            }
            sb.append("  date_day = #{day} ");
            flag=Boolean.TRUE;
        }
        if (!EmptyUtil.isEmpty(hour)) {
            if (flag){
                sb.append(" and ");
            }
            sb.append(" date_hour = #{hour} ");
        }
        sb.append(" ORDER BY weight");
        return sb.toString();
    }
}
