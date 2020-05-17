package com.xuesi.resultVo;

import lombok.Data;

@Data
public class R {
    private int code;
    private String msg;
    private Object data;

    public static R setOK(Object data){
        R r=new R();
        r.setCode(200);
        r.setMsg("OK");
        r.setData(data);
        return r;
    }
    public static R setERROR(){
        R r=new R();
        r.setCode(400);
        r.setMsg("ERROR");
        r.setData(null);
        return r;
    }

}
