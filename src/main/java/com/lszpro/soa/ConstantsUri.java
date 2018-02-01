package com.lszpro.soa;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月11日 下午2:14:22
 * @description
 */
public class ConstantsUri {


//    Menu("菜单","/admin/menu"),
//    MenuInfo("菜单单个","/admin/menu/{id}");
	  
    public static final ConstantsUri Menu =  new ConstantsUri("菜单","/admin/menu");
    public static final ConstantsUri MenuInfo =  new ConstantsUri("菜单详细","/admin/menu/{id}");

    public String describe;

    public String uri;
    public String getInfo(){
    	return uri+"/{id}";
    }
    public ConstantsUri(String describe, String uri) {
        this.describe = describe;
        this.uri = uri;
    }

    public String toString() {
        return this.uri;
    }
}
