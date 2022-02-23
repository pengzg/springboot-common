package xyz.carjoy.question.common.base.model;


import java.io.Serializable;

public class BaseCategory implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	//主键	
	private String bc_id;
	//所属公司	
	private String bc_orgid;
	//上级父类ID	
	private String bc_parentid;
	//客户类别编码	
	private String bc_code;
	//客户类别名称	
	private String bc_name;
	//状态	
	private Integer bc_state;
	//备注	
	private String bc_remarks;
	//最后修改人	
	private String bc_updateuserid;
	//添加时间	
	private String bc_addtime;
	//最后修改时间	
	private String bc_updatetime;
	//有效标志位 1 有效 2 删除	
	private Integer bc_dr;
	//更新时间	
	private String bc_ts;
	//版本	
	private Integer bc_version;
	//columns END

	public String getBc_id() {
		return bc_id;
	}	
	public void setBc_id(String bc_id) {
		this.bc_id = bc_id;
	}
	
	public String getBc_orgid() {
		return bc_orgid;
	}	
	public void setBc_orgid(String bc_orgid) {
		this.bc_orgid = bc_orgid;
	}
	
	public String getBc_parentid() {
		return bc_parentid;
	}	
	public void setBc_parentid(String bc_parentid) {
		this.bc_parentid = bc_parentid;
	}
	
	public String getBc_code() {
		return bc_code;
	}	
	public void setBc_code(String bc_code) {
		this.bc_code = bc_code;
	}
	
	public String getBc_name() {
		return bc_name;
	}	
	public void setBc_name(String bc_name) {
		this.bc_name = bc_name;
	}
	
	public Integer getBc_state() {
		return bc_state;
	}	
	public void setBc_state(Integer bc_state) {
		this.bc_state = bc_state;
	}
	
	public String getBc_remarks() {
		return bc_remarks;
	}	
	public void setBc_remarks(String bc_remarks) {
		this.bc_remarks = bc_remarks;
	}
	
	public String getBc_updateuserid() {
		return bc_updateuserid;
	}	
	public void setBc_updateuserid(String bc_updateuserid) {
		this.bc_updateuserid = bc_updateuserid;
	}
	
	public String getBc_addtime() {
		return bc_addtime;
	}	
	public void setBc_addtime(String bc_addtime) {
		this.bc_addtime = bc_addtime;
	}
	
	public String getBc_updatetime() {
		return bc_updatetime;
	}	
	public void setBc_updatetime(String bc_updatetime) {
		this.bc_updatetime = bc_updatetime;
	}
	
	public Integer getBc_dr() {
		return bc_dr;
	}	
	public void setBc_dr(Integer bc_dr) {
		this.bc_dr = bc_dr;
	}
	
	public String getBc_ts() {
		return bc_ts;
	}	
	public void setBc_ts(String bc_ts) {
		this.bc_ts = bc_ts;
	}
	
	public Integer getBc_version() {
		return bc_version;
	}	
	public void setBc_version(Integer bc_version) {
		this.bc_version = bc_version;
	}
	

}

