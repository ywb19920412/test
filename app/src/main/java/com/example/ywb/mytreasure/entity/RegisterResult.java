package com.example.ywb.mytreasure.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/21.
 */
public class RegisterResult {

    @SerializedName("errcode")
    private int code;

    @SerializedName("errmsg")
    private String msg;

    @SerializedName("tokenid")
    private int tokenId;

    public RegisterResult(int code, String msg, int tokenId) {
        this.code = code;
        this.msg = msg;
        this.tokenId = tokenId;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getTokenId() {
        return tokenId;
    }
}
