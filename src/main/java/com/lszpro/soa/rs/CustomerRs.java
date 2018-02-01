package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.CustomerBO;

import java.util.List;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-18
 * 网点
 */
public class CustomerRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<CustomerBO> dataList;
	    private CustomerBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<CustomerBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<CustomerBO> dataList) {
		this.dataList = dataList;
	}

	public CustomerBO getData() {
		return data;
	}

	public void setData(CustomerBO data) {
		this.data = data;
	}
}