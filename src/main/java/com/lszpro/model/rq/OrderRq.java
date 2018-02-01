package com.lszpro.model.rq;
 
import com.lszpro.soa.common.BaseRq;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据
 */

public class OrderRq extends BaseRq {

private static final long serialVersionUID = 1L;
private String name;

public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
}