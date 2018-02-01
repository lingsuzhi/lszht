package com.lszpro.entity.rs;



import com.lszpro.entity.bo.TopicDiseasesBO;
import com.lszpro.soa.common.BaseResponse;

import java.util.List;

public class TopicDiseasesRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<TopicDiseasesBO> dataList;
	    private TopicDiseasesBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<TopicDiseasesBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<TopicDiseasesBO> dataList) {
		this.dataList = dataList;
	}

	public TopicDiseasesBO getData() {
		return data;
	}

	public void setData(TopicDiseasesBO data) {
		this.data = data;
	}
}
