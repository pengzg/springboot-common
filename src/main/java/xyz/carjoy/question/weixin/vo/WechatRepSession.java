package xyz.carjoy.question.weixin.vo;

public class WechatRepSession {
    /**
     *
     */
    private static final long serialVersionUID = 2363986589638161463L;
    private String openid;	//用户唯一标识
    private String session_key;	//会话密钥
    private String unionid;	//用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。具体参看UnionID机制说明
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getSession_key() {
        return session_key;
    }
    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
