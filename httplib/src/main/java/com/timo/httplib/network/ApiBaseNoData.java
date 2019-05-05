package com.timo.httplib.network;

import com.timo.httplib.network.basebean.BaseBean;

public class ApiBaseNoData extends BaseBean {

    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
