package com.qgstudio.root.constance;

/**
 * @author linxu
 * @date 2019/5/25
 */
public enum Message {
    /**
     * msg
     */
    OK("请求成功！"),
    TIME_OUT("处理超时！"),
    ERROR("服务器端出现异常！"),
    FORMAT_ERROR("数据格式错误，务必检查！"),
    CIRCLE_NOT_EXIST("该区域不存在！")
    ;

    Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    private String msg;
}
