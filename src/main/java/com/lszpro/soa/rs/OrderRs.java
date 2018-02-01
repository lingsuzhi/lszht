package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.OrderBO;

import java.util.List;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据
 */
public class OrderRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<OrderBO> dataList;
	    private OrderBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<OrderBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<OrderBO> dataList) {
		this.dataList = dataList;
	}

	public OrderBO getData() {
		return data;
	}

	public void setData(OrderBO data) {
		this.data = data;
	}
}