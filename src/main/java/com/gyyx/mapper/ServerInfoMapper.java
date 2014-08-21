package com.gyyx.mapper;

import java.util.HashMap;
import java.util.List;

import com.gyyx.model.ServerInfo;

public interface ServerInfoMapper {

	public List<ServerInfo> queryListByGameId(String gameId);
	 public void updateServerName(HashMap<String,String> map);
}
