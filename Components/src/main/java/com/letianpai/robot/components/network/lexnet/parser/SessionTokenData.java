package com.letianpai.robot.components.network.lexnet.parser;

public class SessionTokenData {

    private String access_key;
    private String bucket;
    private String object_key;
    private String object_pre_key;
    private String secret_key;
    private String session_token;
    private long expire_time;

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject_key() {
        return object_key;
    }

    public void setObject_key(String object_key) {
        this.object_key = object_key;
    }

    public String getObject_pre_key() {
        return object_pre_key;
    }

    public void setObject_pre_key(String object_pre_key) {
        this.object_pre_key = object_pre_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    @Override
    public String toString() {
        return "{" +
                "access_key:'" + access_key + '\'' +
                ", bucket:'" + bucket + '\'' +
                ", object_key:'" + object_key + '\'' +
                ", object_pre_key:'" + object_pre_key + '\'' +
                ", secret_key:'" + secret_key + '\'' +
                ", session_token:'" + session_token + '\'' +
                ", expire_time:" + expire_time +
                '}';
    }
}
