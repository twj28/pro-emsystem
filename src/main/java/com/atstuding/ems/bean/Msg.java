package com.atstuding.ems.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassNmae Msg
 * @Description 通用返回的 类
 * @Author twj280
 * @Date 2022/7/15 15:26
 * @Version 1.0
 **/
public class Msg {
    //状态码 100->成功， 200->失败
    private int code;

    //提示信息
    private String msg;

    //返回给浏览器的信息
    private Map<String,Object> extend = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功!");
        return  result;
    }
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败!");
        return result;
    }
    public Msg add(String key,Object value){
        Msg result = new Msg();
        this.getExtend().put(key,value);
        return this;
    }
}
