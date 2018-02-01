package com.lszpro.entity.rs;


import com.lszpro.entity.bo.MenuBO;
import com.lszpro.soa.common.BaseResponse;

import java.util.List;

public class MenuRs extends BaseResponse {
	  private static final long serialVersionUID = -7859370887990688693L;
	    private int total;

	    private List<MenuBO> dataList;
	    private MenuBO data;

	    public int getTotal() {
	        return total;
	    }

	    public void setTotal(int total) {
	        this.total = total;
	    }


	    public List<MenuBO> getDataList() {
	        return dataList;
	    }

	    public void setDataList(List<MenuBO> dataList) {
	        this.dataList = dataList;
	    }

	    public MenuBO getData() {
	        return data;
	    }

	    public void setData(MenuBO data) {
	        this.data = data;
	    }
}
