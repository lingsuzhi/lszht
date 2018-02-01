package com.lszpro.soa.rs;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.model.bo.MessageFaceBO;

import java.util.List;

public class MessageFaceRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private Integer total;

	    private List<MessageFaceBO> dataList;
	    private MessageFaceBO data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<MessageFaceBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<MessageFaceBO> dataList) {
		this.dataList = dataList;
	}

	public MessageFaceBO getData() {
		return data;
	}

	public void setData(MessageFaceBO data) {
		this.data = data;
	}
}