package com.qgstudio.root.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author linxu
 * @date 2019/5/25
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractCircle<T>{
    protected int id;
    protected String msg;
    protected double weight;
    /**
     * this should be transfer to <longitude,latitude> array
     */
    @JsonIgnore
    protected String latAndLonCircle;

    /**
     * 用于转化latAndLonCircle；允许不同的转化方式
     * @return T
     */
    protected abstract T transfer();

}
