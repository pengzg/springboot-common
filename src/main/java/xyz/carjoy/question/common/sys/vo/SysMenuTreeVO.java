package xyz.carjoy.question.common.sys.vo;

import org.apache.commons.lang.StringUtils;
import xyz.carjoy.question.utils.Tree;

public class SysMenuTreeVO extends Tree {
    private String sm_id;
    private String sm_name;//菜单名称
    private String sm_typeid;//菜单类型
    private String sm_typeid_nameref;
    private Integer sm_seq;//序号
    private String sm_iconcls;//图标
    private String sm_status;//状态
    private String sm_status_nameref;
    private String sm_systemid;//所属系统
    private String sm_systemid_nameref;
    private String sm_description;//描述
    private boolean sm_ischeck = false;
    // 路由
    private String sm_url;



    public String getSm_typeid_nameref() {
        if(StringUtils.isNotBlank(sm_systemid)){
            if(sm_systemid.equals("1")){
                sm_typeid_nameref = "菜单";
            }else{
                sm_typeid_nameref = "功能";
            }
        }
        return sm_typeid_nameref;
    }

    public void setSm_typeid_nameref(String sm_typeid_nameref) {
        this.sm_typeid_nameref = sm_typeid_nameref;
    }

    public String getSm_status_nameref() {
        if(StringUtils.isNotBlank(sm_status)){
            if(sm_status.equals("1")){
                sm_status_nameref = "启用";
            }else{
                sm_status_nameref = "禁用";
            }
        }
        return sm_status_nameref;
    }

    public void setSm_status_nameref(String sm_status_nameref) {
        this.sm_status_nameref = sm_status_nameref;
    }

    public String getSm_systemid_nameref() {
        if(StringUtils.isNotBlank(sm_systemid)){
            if(sm_systemid.equals("1005")){
                sm_systemid_nameref = "批发商管理系统";
            }else{
                sm_systemid_nameref = "";
            }
        }
        return sm_systemid_nameref;
    }

    public void setSm_systemid_nameref(String sm_systemid_nameref) {
        this.sm_systemid_nameref = sm_systemid_nameref;
    }


    public boolean isSm_ischeck() {
        return sm_ischeck;
    }

    public void setSm_ischeck(boolean sm_ischeck) {
        this.sm_ischeck = sm_ischeck;
    }

    public String getSm_name() {
        return sm_name;
    }

    public void setSm_name(String sm_name) {
        this.sm_name = sm_name;
    }

    public String getSm_id() {
        return sm_id;
    }

    public void setSm_id(String sm_id) {
        this.sm_id = sm_id;
    }

    public String getSm_typeid() {
        return sm_typeid;
    }

    public void setSm_typeid(String sm_typeid) {
        this.sm_typeid = sm_typeid;
    }

    public String getSm_iconcls() {
        return sm_iconcls;
    }

    public void setSm_iconcls(String sm_iconcls) {
        this.sm_iconcls = sm_iconcls;
    }

    public String getSm_status() {
        return sm_status;
    }

    public void setSm_status(String sm_status) {
        this.sm_status = sm_status;
    }

    public String getSm_systemid() {
        return sm_systemid;
    }

    public void setSm_systemid(String sm_systemid) {
        this.sm_systemid = sm_systemid;
    }

    public String getSm_description() {
        return sm_description;
    }

    public void setSm_description(String sm_description) {
        this.sm_description = sm_description;
    }

    public Integer getSm_seq() {
        return sm_seq;
    }

    public void setSm_seq(Integer sm_seq) {
        this.sm_seq = sm_seq;
    }

    public String getSm_url() {
        return sm_url;
    }

    public void setSm_url(String sm_url) {
        this.sm_url = sm_url;
    }





}