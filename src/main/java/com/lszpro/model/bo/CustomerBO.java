package com.lszpro.model.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class CustomerBO{
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

    /** 类别 */
    private String typeId;
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeId() {
        return typeId;
    }

    /** 等级 */
    private Integer level;
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getLevel() {
        return level;
    }

    /** 电话 */
    private String phone;
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    /** 邮箱 */
    private String email;
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    /** 备注 */
    private String remake;
    public void setRemake(String remake) {
        this.remake = remake;
    }
    public String getRemake() {
        return remake;
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
