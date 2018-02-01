package com.lszpro.entity.rs;

import com.lszpro.model.bo.ProductTypeBO;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/12 18:20
 */
public class ProductTypeNode {

    private ProductTypeBO productType;
    private String name;
    private List<ProductTypeNode> children;

    public ProductTypeBO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeBO productType) {
        this.productType = productType;
    }

    public String getName() {
        if(productType ==null)return null;
        return productType.getName();
    }


    public List<ProductTypeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ProductTypeNode> children) {
        this.children = children;
    }
}
