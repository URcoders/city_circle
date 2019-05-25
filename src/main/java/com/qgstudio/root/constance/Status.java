package com.qgstudio.root.constance;

/**
 * @author linxu
 * @date 2019/5/25
 */
public enum Status {
    OK(200),
    ERROR(500),
    UNKONWN(400);
    private Integer status;
    Status(Integer status) {
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }
}
