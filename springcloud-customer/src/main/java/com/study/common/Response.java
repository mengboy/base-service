package com.study.common;



import java.util.List;

/**
 * 返回结果集，包括：
 *  查询的结果
 *  返回值状态
 *  返回信息
 * Created by admin on 2016/11/5.
 */
public class Response {
    protected int status;
    protected Object body;
    protected String message;

    public List<String> getListMessage() {
        return listMessage;
    }



    public void setListMessage(List<String> listMessage) {
        this.listMessage = listMessage;
    }

    protected List<String> listMessage;
    public Response() {
    }

    public Response(int status) {
        this.status = status;
    }

    public Response(int status, String message) {
        this(status);
        this.message = message;
    }

    public Response(int status, Object body) {
        this(status);
        this.body = body;
    }

    public Response(int status, Object body, List<String> listMessage) {
        this(status);
        this.body = body;
        this.listMessage = listMessage;
    }

    public Response(int status, String message, Object body) {
        this(status, message);
        this.body = body;
    }



    public Response(int status, String message, List<String> listMessage, Object body) {
        this(status, body, listMessage);
        this.message = message;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
