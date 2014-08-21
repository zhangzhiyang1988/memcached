package com.gyyx.mapper;

import java.util.HashMap;
import java.util.List;

import com.gyyx.model.GameInfo;

public interface GameInfoMapper {
	    public List<GameInfo> getGameInfo(); 
	    public void updateGameName(HashMap<String,String> map);
}
