package xyz.carjoy.question.common.base.vo;


import xyz.carjoy.question.utils.Tree;

public class BaseDataTreeVO extends Tree {
    private static final long serialVersionUID = -7862519513768196516L;
    private String bd_id;
    private String bd_name;
    private String bd_code;
    private Integer bd_order;
    private String bd_status;



    public String getBd_id() {
        return bd_id;
    }
    public void setBd_id(String bd_id) {
        this.bd_id = bd_id;
    }
    public String getBd_name() {
        return bd_name;
    }
    public void setBd_name(String bd_name) {
        this.bd_name = bd_name;
    }
    public String getBd_code() {
        return bd_code;
    }
    public void setBd_code(String bd_code) {
        this.bd_code = bd_code;
    }
    public Integer getBd_order() {
        return bd_order;
    }
    public void setBd_order(Integer bd_order) {
        this.bd_order = bd_order;
    }
    public String getBd_status() {
        return bd_status;
    }
    public void setBd_status(String bd_status) {
        this.bd_status = bd_status;
    }

}
