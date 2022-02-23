package xyz.carjoy.question.common.base.model;

import java.io.Serializable;

public class BaseBrand implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	//主键	
	private String bb_id;
	//所属公司	
	private Integer bb_orgid;
	//品牌编码	
	private String bb_code;
	//品牌名称	
	private String bb_title;
	//品牌简介	
	private String bb_intro;
	//品牌封面	
	private String bb_cover;
	//国家	
	private String bb_countyid;
	//品牌区域1国内2国际	
	private String bb_area;
	//品牌级别1一线品牌,2二线品牌,3三线品牌 	
	private String bb_level;
	//用于无限分类	
	private String bb_path;
	//简介	
	private String bb_content;
	//网络链接	
	private String bb_url;
	//	
	private Integer bb_sort;
	//状态 1是启用 0是禁用	
	private Integer bb_state;
	//添加时间	
	private String bb_addtime;
	//更新时间	
	private String bb_updatetime;
	//删除标志位 1未删除2已删除	
	private Integer bb_dr;
	//是否前台显示1 是 0 否	
	private Integer bb_is_show;
	//columns END

	public String getBb_id() {
		return bb_id;
	}	
	public void setBb_id(String bb_id) {
		this.bb_id = bb_id;
	}
	
	public Integer getBb_orgid() {
		return bb_orgid;
	}	
	public void setBb_orgid(Integer bb_orgid) {
		this.bb_orgid = bb_orgid;
	}
	
	public String getBb_code() {
		return bb_code;
	}	
	public void setBb_code(String bb_code) {
		this.bb_code = bb_code;
	}
	
	public String getBb_title() {
		return bb_title;
	}	
	public void setBb_title(String bb_title) {
		this.bb_title = bb_title;
	}
	
	public String getBb_intro() {
		return bb_intro;
	}	
	public void setBb_intro(String bb_intro) {
		this.bb_intro = bb_intro;
	}
	
	public String getBb_cover() {
		return bb_cover;
	}	
	public void setBb_cover(String bb_cover) {
		this.bb_cover = bb_cover;
	}
	
	public String getBb_countyid() {
		return bb_countyid;
	}	
	public void setBb_countyid(String bb_countyid) {
		this.bb_countyid = bb_countyid;
	}
	
	public String getBb_area() {
		return bb_area;
	}	
	public void setBb_area(String bb_area) {
		this.bb_area = bb_area;
	}
	
	public String getBb_level() {
		return bb_level;
	}	
	public void setBb_level(String bb_level) {
		this.bb_level = bb_level;
	}
	
	public String getBb_path() {
		return bb_path;
	}	
	public void setBb_path(String bb_path) {
		this.bb_path = bb_path;
	}
	
	public String getBb_content() {
		return bb_content;
	}	
	public void setBb_content(String bb_content) {
		this.bb_content = bb_content;
	}
	
	public String getBb_url() {
		return bb_url;
	}	
	public void setBb_url(String bb_url) {
		this.bb_url = bb_url;
	}
	
	public Integer getBb_sort() {
		return bb_sort;
	}	
	public void setBb_sort(Integer bb_sort) {
		this.bb_sort = bb_sort;
	}
	
	public Integer getBb_state() {
		return bb_state;
	}	
	public void setBb_state(Integer bb_state) {
		this.bb_state = bb_state;
	}
	
	public String getBb_addtime() {
		return bb_addtime;
	}	
	public void setBb_addtime(String bb_addtime) {
		this.bb_addtime = bb_addtime;
	}
	
	public String getBb_updatetime() {
		return bb_updatetime;
	}	
	public void setBb_updatetime(String bb_updatetime) {
		this.bb_updatetime = bb_updatetime;
	}
	
	public Integer getBb_dr() {
		return bb_dr;
	}	
	public void setBb_dr(Integer bb_dr) {
		this.bb_dr = bb_dr;
	}
	
	public Integer getBb_is_show() {
		return bb_is_show;
	}	
	public void setBb_is_show(Integer bb_is_show) {
		this.bb_is_show = bb_is_show;
	}
	

}

