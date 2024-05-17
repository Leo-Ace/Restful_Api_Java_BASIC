package com.fe.entities;

public class Result {
	private int code;
	private String message;
	
	public Result() {
		// TODO Auto-generated constructor stub
	}

	public Result(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
