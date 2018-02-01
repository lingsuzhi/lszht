package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.ProductCompanyBO;

import java.util.List;

public class ProductCompanyRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<ProductCompanyBO> dataList;
	    private ProductCompanyBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<ProductCompanyBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<ProductCompanyBO> dataList) {
		this.dataList = dataList;
	}

	public ProductCompanyBO getData() {
		return data;
	}

	public void setData(ProductCompanyBO data) {
		this.data = data;
	}
}