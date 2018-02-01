package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.OrderSetBO;

import java.util.List;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据配置
 */
public class OrderSetRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<OrderSetBO> dataList;
	    private OrderSetBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<OrderSetBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<OrderSetBO> dataList) {
		this.dataList = dataList;
	}

	public OrderSetBO getData() {
		return data;
	}

	public void setData(OrderSetBO data) {
		this.data = data;
	}
}