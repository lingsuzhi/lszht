package com.lszpro.model.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class ProductCompanyBO{
    /** key */
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    /** 名称 */
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    /** 权重 */
    private Integer weight;
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Integer getWeight() {
        return weight;
    }

    /** 排序 */
    private Integer sort;
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getSort() {
        return sort;
    }

    /** 状态 */
    private Boolean status;
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Boolean getStatus() {
        return status;
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
    private String remark;
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }

}
