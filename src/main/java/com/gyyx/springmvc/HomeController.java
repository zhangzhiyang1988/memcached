package com.gyyx.springmvc;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import net.rubyeye.xmemcached.utils.hibernate.XmemcachedClientFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.gyyx.factory.XMemClientFactory;
import com.gyyx.mapper.GameInfoMapper;
import com.gyyx.mapper.ServerInfoMapper;
import com.gyyx.model.GameInfo;
import com.gyyx.model.ServerInfo;
import com.gyyx.utils.FileUtil;
import com.gyyx.utils.MyBatisUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	SqlSessionFactory sqlSessionFactory = null;
	MemcachedClient client;
	final String cacheKey = "zzymemcached_test";
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	public HomeController() {
		client = XMemClientFactory.getInstance();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		{
			sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		}
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<GameInfo> games = null;

			GameInfoMapper gameMapper = sqlSession
					.getMapper(GameInfoMapper.class);
			games = gameMapper.getGameInfo();

			System.out.println(games.get(0).getName());
			model.addAttribute("games", games);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return "home";
	}

	@RequestMapping(value = "/getServerList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getServerList(Locale locale, Model model,
			@RequestParam("gameId") String gameId) {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		List<ServerInfo> servers = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ServerInfoMapper serverInfoMapper = sqlSession
				.getMapper(ServerInfoMapper.class);
		try {
			servers = client.get(cacheKey);
			if (servers == null) {
				FileUtil.writeTofile("start to read data");
				servers = serverInfoMapper.queryListByGameId(gameId);
				client.add(cacheKey, 0, servers);

				FileUtil.writeTofile("start memcached");
			} else {
				FileUtil.writeTofile("statrt read data from memcached");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String s = gson.toJson(servers);
		return s;
	}

	public void updateServerName(HashMap<String, String> map) {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ServerInfoMapper serverMapper = sqlSession
				.getMapper(ServerInfoMapper.class);
		serverMapper.updateServerName(map);
		sqlSession.commit();
		sqlSession.close();
		try {
			client.delete(cacheKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
