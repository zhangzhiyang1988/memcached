package com.gyyx.model;

import java.io.Serializable;

public class ServerInfo  implements Serializable{

	private String code ;
	private String gameId ;
	private String serverName ;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	
}
