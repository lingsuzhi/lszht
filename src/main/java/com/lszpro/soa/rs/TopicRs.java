package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.TopicBO;

import java.util.List;

public class TopicRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<TopicBO> dataList;
	    private TopicBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<TopicBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<TopicBO> dataList) {
		this.dataList = dataList;
	}

	public TopicBO getData() {
		return data;
	}

	public void setData(TopicBO data) {
		this.data = data;
	}
}
