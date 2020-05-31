package com.stackroute.favouriteservice.exception;

public class FoodNotFoundException extends Exception {
	String msg="";

	public FoodNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
