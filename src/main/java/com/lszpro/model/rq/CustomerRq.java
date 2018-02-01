package com.lszpro.model.rq;
 
import com.lszpro.soa.common.BaseRq;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-18
 * 网点
 */

public class CustomerRq extends BaseRq {

private static final long serialVersionUID = 1L;
private String name;
private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
}