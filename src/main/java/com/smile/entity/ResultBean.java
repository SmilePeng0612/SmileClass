package com.smile.entity;

/*
    在实际开发中，并不会直接将数据转换成json，而是在返回数据的同时，伴随着状态、消息提示的返回！
    为了能够将这些数据一并转换成json格式的数据返回，我们提供一个结果集对象，在结果对象中，设置
    status 表示状态
    message 表示消息
    data 表示数据
 */
public class ResultBean {
    //响应的状态值，比如404 500
    private Integer status;
    //响应的消息信息，比如成功、失败
    private String message;
    //响应的数据，Object类型的原因是为了满足所有数据的响应
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultBean() {
    }

    public ResultBean(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
