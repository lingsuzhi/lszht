package com.lszpro.entity.rs;

import com.lszpro.model.bo.CustomerTypeBO;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/12 18:20
 */
public class CustomerTypeNode {

    private CustomerTypeBO customerType;
    private String name;
    private List<CustomerTypeNode> children;

    public CustomerTypeBO getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeBO customerType) {
        this.customerType = customerType;
    }

    public String getName() {
        if(customerType ==null)return null;
        return customerType.getName();
    }


    public List<CustomerTypeNode> getChildren() {
        return children;
    }

    public void setChildren(List<CustomerTypeNode> children) {
        this.children = children;
    }
}
