package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.ProductTypeBO;

import java.util.List;

public class ProductTypeRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<ProductTypeBO> dataList;
	    private ProductTypeBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<ProductTypeBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<ProductTypeBO> dataList) {
		this.dataList = dataList;
	}

	public ProductTypeBO getData() {
		return data;
	}

	public void setData(ProductTypeBO data) {
		this.data = data;
	}
}