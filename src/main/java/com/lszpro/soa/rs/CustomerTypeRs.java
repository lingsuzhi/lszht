package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.CustomerTypeBO;

import java.util.List;

public class CustomerTypeRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<CustomerTypeBO> dataList;
	    private CustomerTypeBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<CustomerTypeBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<CustomerTypeBO> dataList) {
		this.dataList = dataList;
	}

	public CustomerTypeBO getData() {
		return data;
	}

	public void setData(CustomerTypeBO data) {
		this.data = data;
	}
}