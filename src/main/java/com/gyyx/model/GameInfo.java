package com.gyyx.model;

import java.io.Serializable;

public class GameInfo implements Serializable{
	private static final long serialVersionUID = -3896605600471191953L;
	private String name ;
	private String code ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public GameInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GameInfo(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	
}
