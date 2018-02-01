package com.lszpro.entity.rq;


import com.lszpro.soa.common.BaseRq;

public class MenuRq extends BaseRq {
	private String parentId;
private Integer size ;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
