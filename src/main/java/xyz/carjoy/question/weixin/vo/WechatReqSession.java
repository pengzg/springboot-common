package xyz.carjoy.question.weixin.vo;

public class WechatReqSession {

    /**
     *
     */
    private static final long serialVersionUID = 8218739224558158653L;
    private String appId;//		小程序唯一标识
    private String secret;//		小程序的 app secret
    private String js_code;//		登录时获取的 code
    private String grant_type;//	填写为 authorization_code

    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String getJs_code() {
        return js_code;
    }
    public void setJs_code(String js_code) {
        this.js_code = js_code;
    }
    public String getGrant_type() {
        return grant_type;
    }
    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
