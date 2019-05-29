package com.qgstudio.root.dao;

import com.qgstudio.root.models.CityCircle;
import com.qgstudio.root.models.Point;
import com.qgstudio.root.utils.EmptyUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author linxu
 * @date 2019/5/29
 * this processor can do the compute work outline.
 * such as compute the centre point'lat and longitude,and storage.
 */
public class Processor {
    private static final String BASE_SQL="UPDATE  circle SET centre_lon=#{lon},centre_lat=#{lat} WHERE id=#{id}";
    public String computeCentrePointLonAndLat(@Param("lon")double lon,@Param("lat")double lat,@Param("id")int id){
        return BASE_SQL;
    }
}
