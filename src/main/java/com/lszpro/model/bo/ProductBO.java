package com.lszpro.model.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class ProductBO{
    /** 编号 */
    private String number;
    /** 规格 */
    private String  spec;
    /**  条形码*/
    private String barcode;
    /**  生产厂家*/
    private String  manufacturer;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /** 类别id */
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
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

    /** 描述 */
    private String description;
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    /** 图片 */
    private String image;
    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    /** 成本价 */
    private Double cost;
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Double getCost() {
        return cost;
    }

    /** 价格 */
    private Double proce;
    public void setProce(Double proce) {
        this.proce = proce;
    }
    public Double getProce() {
        return proce;
    }

    /** 类别 */
    private String typeId;
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeId() {
        return typeId;
    }

    /** 单位 */
    private String company;
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompany() {
        return company;
    }

    /** 等级 */
    private Integer level;
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getLevel() {
        return level;
    }

    /** 总数 */
    private Double stock;
    public void setStock(Double stock) {
        this.stock = stock;
    }
    public Double getStock() {
        return stock;
    }

    /** 已发放数量 */
    private Double count;
    public void setCount(Double count) {
        this.count = count;
    }
    public Double getCount() {
        return count;
    }

    /** 创建时间 */
    private Date createTime;
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }

    /** 状态 */
    private Boolean status;
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Boolean getStatus() {
        return status;
    }

}
