package xyz.carjoy.question.member.model;

import java.io.Serializable;

public class MemberBase implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	//主键	
	private String mb_id;
	//应用号	
	private String mb_applicationid;
	//所属公司	
	private String mb_orgid;
	//会员编码
	private String mb_code;
	//会员昵称	
	private String mb_nickname;
	//会员姓名	
	private String mb_name;
	//联系电话	
	private String mb_mobile;
	//微信id	
	private String mb_uuid;
	//	
	private String mb_openid;
	//用户头像	
	private String mb_img;
	//所属城市	
	private String mb_city;
	//性别	
	private String mb_sex;
	//关注时间	
	private String mb_followtime;
	//用户类型 1 普通用户 2 商家（管理员） 3代理商 4 运营商 5核销员用户	
	private Integer mb_usertype;
	//生日	
	private String mb_birthday;
	//取消关注时间	
	private String mb_unfollowtime;
	//最后一次登陆时间	
	private String mb_lastlogintime;
	//用户状态 0禁用  1启用	
	private Integer mb_state;
	//有效开始时间	
	private String mb_statdate;
	//有效结束时间	
	private String mb_enddate;
	//ts	
	private String mb_ts;
	//添加时间	
	private String mb_addtime;
	//更新时间 	
	private String mb_updatetime;
	//dr	
	private Integer mb_dr;
	//columns END

	public String getMb_id() {
		return mb_id;
	}	
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	
	public String getMb_applicationid() {
		return mb_applicationid;
	}	
	public void setMb_applicationid(String mb_applicationid) {
		this.mb_applicationid = mb_applicationid;
	}
	
	public String getMb_orgid() {
		return mb_orgid;
	}	
	public void setMb_orgid(String mb_orgid) {
		this.mb_orgid = mb_orgid;
	}

	public String getMb_code() {
		return mb_code;
	}

	public void setMb_code(String mb_code) {
		this.mb_code = mb_code;
	}

	public String getMb_nickname() {
		return mb_nickname;
	}	
	public void setMb_nickname(String mb_nickname) {
		this.mb_nickname = mb_nickname;
	}
	
	public String getMb_name() {
		return mb_name;
	}	
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	
	public String getMb_mobile() {
		return mb_mobile;
	}	
	public void setMb_mobile(String mb_mobile) {
		this.mb_mobile = mb_mobile;
	}
	
	public String getMb_uuid() {
		return mb_uuid;
	}	
	public void setMb_uuid(String mb_uuid) {
		this.mb_uuid = mb_uuid;
	}
	
	public String getMb_openid() {
		return mb_openid;
	}	
	public void setMb_openid(String mb_openid) {
		this.mb_openid = mb_openid;
	}
	
	public String getMb_img() {
		return mb_img;
	}	
	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}
	
	public String getMb_city() {
		return mb_city;
	}	
	public void setMb_city(String mb_city) {
		this.mb_city = mb_city;
	}
	
	public String getMb_sex() {
		return mb_sex;
	}	
	public void setMb_sex(String mb_sex) {
		this.mb_sex = mb_sex;
	}
	
	public String getMb_followtime() {
		return mb_followtime;
	}	
	public void setMb_followtime(String mb_followtime) {
		this.mb_followtime = mb_followtime;
	}
	
	public Integer getMb_usertype() {
		return mb_usertype;
	}	
	public void setMb_usertype(Integer mb_usertype) {
		this.mb_usertype = mb_usertype;
	}
	
	public String getMb_birthday() {
		return mb_birthday;
	}	
	public void setMb_birthday(String mb_birthday) {
		this.mb_birthday = mb_birthday;
	}
	
	public String getMb_unfollowtime() {
		return mb_unfollowtime;
	}	
	public void setMb_unfollowtime(String mb_unfollowtime) {
		this.mb_unfollowtime = mb_unfollowtime;
	}
	
	public String getMb_lastlogintime() {
		return mb_lastlogintime;
	}	
	public void setMb_lastlogintime(String mb_lastlogintime) {
		this.mb_lastlogintime = mb_lastlogintime;
	}
	
	public Integer getMb_state() {
		return mb_state;
	}	
	public void setMb_state(Integer mb_state) {
		this.mb_state = mb_state;
	}
	
	public String getMb_statdate() {
		return mb_statdate;
	}	
	public void setMb_statdate(String mb_statdate) {
		this.mb_statdate = mb_statdate;
	}
	
	public String getMb_enddate() {
		return mb_enddate;
	}	
	public void setMb_enddate(String mb_enddate) {
		this.mb_enddate = mb_enddate;
	}
	
	public String getMb_ts() {
		return mb_ts;
	}	
	public void setMb_ts(String mb_ts) {
		this.mb_ts = mb_ts;
	}
	
	public String getMb_addtime() {
		return mb_addtime;
	}	
	public void setMb_addtime(String mb_addtime) {
		this.mb_addtime = mb_addtime;
	}
	
	public String getMb_updatetime() {
		return mb_updatetime;
	}	
	public void setMb_updatetime(String mb_updatetime) {
		this.mb_updatetime = mb_updatetime;
	}
	
	public Integer getMb_dr() {
		return mb_dr;
	}	
	public void setMb_dr(Integer mb_dr) {
		this.mb_dr = mb_dr;
	}
	

}

