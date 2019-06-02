package com.qgstudio.root.dao;

import com.qgstudio.root.models.CityCircle;
import com.qgstudio.root.models.RouteCityCircle;
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

    @UpdateProvider(type = Processor.class, method = "computeCentrePointLonAndLat")
    void updateCentrePoint(@Param("lon") double lon, @Param("lat") double lat, @Param("id") int id);

    /**************************route_circle**********************************************/
    @SelectProvider(type = Selector.class, method = "selectRouteCircle")
    @Results(
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "weight", column = "route_weight"),
                    @Result(property = "fromIndex", column = "from_idx"),
                    @Result(property = "toIndex", column = "to_idx")
            }
    )
    List<RouteCityCircle> queryRouteCityCircle(@Param("month") String month, @Param("day") String day, @Param("hour") String hour);

    @Select("SELECT lat_lon_circle ,centre_lon,centre_lat from circle where idx=#{idx} limit 1")
    @Results(
            {
                    @Result(property = "tempLonAndLat", column = "lat_lon_circle"),
                    @Result(property = "tempCentreLon", column = "centre_lon"),
                    @Result(property = "tempCentreLat", column = "centre_lat")
            }
    )
    RouteCityCircle selectCentrePointAndLonLat(@Param("idx") int idx);


}
