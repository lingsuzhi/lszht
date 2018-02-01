package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.ProductBO;

import java.util.List;

public class ProductRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<ProductBO> dataList;
	    private ProductBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<ProductBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<ProductBO> dataList) {
		this.dataList = dataList;
	}

	public ProductBO getData() {
		return data;
	}

	public void setData(ProductBO data) {
		this.data = data;
	}
}