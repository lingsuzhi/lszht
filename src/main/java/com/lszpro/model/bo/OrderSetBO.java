package com.lszpro.model.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class OrderSetBO{
    /**  */
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

    /** 前戳 */
    private String leftStr;
    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
    }
    public String getLeftStr() {
        return leftStr;
    }

    /** 标题 */
    private String title;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    /** 单据文件名 */
    private String orderfile;
    public void setOrderfile(String orderfile) {
        this.orderfile = orderfile;
    }
    public String getOrderfile() {
        return orderfile;
    }

    /** 增加库存 */
    private Boolean adds;
    public void setAdds(Boolean adds) {
        this.adds = adds;
    }
    public Boolean getAdds() {
        return adds;
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

}
