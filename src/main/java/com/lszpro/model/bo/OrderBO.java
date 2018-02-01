package com.lszpro.model.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class OrderBO{
    private  String orderSetName;

    public String getOrderSetName() {
        return orderSetName;
    }

    public void setOrderSetName(String orderSetName) {
        this.orderSetName = orderSetName;
    }
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /***单号*/
    private String danhao;

    public String getDanhao() {
        return danhao;
    }

    public void setDanhao(String danhao) {
        this.danhao = danhao;
    }
    /**  */
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    /** 日期 */
    private Date daydate;
    public void setDaydate(Date daydate) {
        this.daydate = daydate;
    }
    public Date getDaydate() {
        return daydate;
    }

    /** 单据类型id */
    private String orderSetId;
    public void setOrderSetId(String orderSetId) {
        this.orderSetId = orderSetId;
    }
    public String getOrderSetId() {
        return orderSetId;
    }

    /** 网点id */
    private String customerId;
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerId() {
        return customerId;
    }

    /** 天流水号 */
    private Integer dayNumber;
    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }
    public Integer getDayNumber() {
        return dayNumber;
    }

    /** 流水号 */
    private Integer allNumber;
    public void setAllNumber(Integer allNumber) {
        this.allNumber = allNumber;
    }
    public Integer getAllNumber() {
        return allNumber;
    }

    /** 总数量 */
    private Double zsl;
    public void setZsl(Double zsl) {
        this.zsl = zsl;
    }
    public Double getZsl() {
        return zsl;
    }

    /** 总金额 */
    private Double zje;
    public void setZje(Double zje) {
        this.zje = zje;
    }
    public Double getZje() {
        return zje;
    }

    /** 实收金额 */
    private Double shishouje;
    public void setShishouje(Double shishouje) {
        this.shishouje = shishouje;
    }
    public Double getShishouje() {
        return shishouje;
    }

    /** 调度id */
    private String dispatchId;
    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }
    public String getDispatchId() {
        return dispatchId;
    }

    /** 调度次数 */
    private Integer dispatchNumber;
    public void setDispatchNumber(Integer dispatchNumber) {
        this.dispatchNumber = dispatchNumber;
    }
    public Integer getDispatchNumber() {
        return dispatchNumber;
    }

    /** 用户id */
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

    /** 管理员id */
    private String adminId;
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public String getAdminId() {
        return adminId;
    }

    /** 负责人 */
    private String fuzeren;
    public void setFuzeren(String fuzeren) {
        this.fuzeren = fuzeren;
    }
    public String getFuzeren() {
        return fuzeren;
    }

    /** 经办人 */
    private String jingbanren;
    public void setJingbanren(String jingbanren) {
        this.jingbanren = jingbanren;
    }
    public String getJingbanren() {
        return jingbanren;
    }

    /** 创建时间 */
    private Date createTime;
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }

    /** 备注 */
    private String remake;
    public void setRemake(String remake) {
        this.remake = remake;
    }
    public String getRemake() {
        return remake;
    }

    /** 保留字段 */
    private String zzz1;
    public void setZzz1(String zzz1) {
        this.zzz1 = zzz1;
    }
    public String getZzz1() {
        return zzz1;
    }

    /** 保留字段2 */
    private String zzz2;
    public void setZzz2(String zzz2) {
        this.zzz2 = zzz2;
    }
    public String getZzz2() {
        return zzz2;
    }

    /** 折扣 */
    private Double discount;
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public Double getDiscount() {
        return discount;
    }

    /** 数据 */
    private String datas;
    public void setDatas(String datas) {
        this.datas = datas;
    }
    public String getDatas() {
        return datas;
    }

}
