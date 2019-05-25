package com.qgstudio.root.dao;

import com.qgstudio.root.models.CityCircle;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Mapper
public interface Dao {

    @SelectProvider(type = Selector.class, method = "selectCircleByDate")
    @Results(
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "msg", column = "msg"),
                    @Result(property = "weight", column = "weight"),
                    @Result(property = "latAndLonCircle", column = "lat_lon_circle"),
                    @Result(property = "month", column = "date_month"),
                    @Result(property = "day", column = "date_day"),
                    @Result(property = "hour", column = "date_hour")
            }
    )
    List<CityCircle> queryCityCle(@Param("month") String month, @Param("day") String day, @Param("hour") String hour);
}
