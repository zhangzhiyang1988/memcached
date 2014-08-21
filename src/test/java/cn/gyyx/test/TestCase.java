package cn.gyyx.test;

import java.util.HashMap;

import org.junit.Test;



import com.gyyx.springmvc.HomeController;
import com.gyyx.utils.FileUtil;

public class TestCase {

	HomeController controler =null;
	
	public TestCase(){
		controler = new HomeController();
	}
	/**
	 * 查询数据库
	 */
	@Test
	public void testGetServer(){
		String servers = controler.getServerList(null, null, "1");
		FileUtil.writeTofile(servers);
	}
	/**
	 * 更新服务器数据库
	 */
	@Test
	public void testUpdate(){
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("code", "1");
		map.put("name", "dragon and sprit");
		controler.updateServerName(map);
		FileUtil.writeTofile("----------------更新后");
	}
	
}
