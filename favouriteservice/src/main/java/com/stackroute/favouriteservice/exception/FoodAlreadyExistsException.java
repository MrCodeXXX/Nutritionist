package com.stackroute.favouriteservice.exception;

public class FoodAlreadyExistsException extends Exception {
	String msg="";

	public FoodAlreadyExistsException(String msg) {
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
