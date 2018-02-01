package com.lszpro.entity.bo;

import java.util.Date;

/**
* Admin: lingsuzhi <554600654@qq.com.com>
* Date: 2017-12-07
*/

public class TopicDiseasesBO {
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

}
