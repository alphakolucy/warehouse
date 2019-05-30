package com.warehouse.util.enums;
 
public enum OutputState { 
	
	MECHANISM_NOT_EXISTS("机构信息不存在"), 
	
	SUCCESS("成功"),//成功
	FAIL( "失败");//失败
	
	  
	private String txt;
	OutputState(  String txt) { 
	   this.setTxt(txt);
    }   
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}   
}
