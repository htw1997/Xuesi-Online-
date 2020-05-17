package com.xuesi.pojo;

import lombok.Data;

import java.util.List;

/**
 * 专门用于封装返回的数据
 */
@Data
public class Result {

    private Integer status = 0;
    private Boolean code;
    private String message = "success";

    //指的是每次去查询数据库时 当前表的数据总量
    private Integer total = 0;

    private List item;

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getItem() {
        return item;
    }

    public void setItem(List item) {
        this.item = item;
    }
}
