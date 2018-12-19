package enn.monitor.security.gateway.token.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnnMonitorConfigServiceTokenCache {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<String, Long> tokenMap = new ConcurrentHashMap<String, Long>();
	private Map<Long, String> tokenIdMap = new ConcurrentHashMap<Long, String>();
	
	public String getTokenId(String token) {
		Long tokenId = -1l;
		tokenId = tokenMap.get(token);
		
		if (tokenId == null) {
			logger.error("the token is illegal, the token is " + token);
			return "";
		}
		
		return "" + tokenId;
	}
	
	public void add(String token, Long id) {
		tokenMap.put(token, id);
		tokenIdMap.put(id, token);
	}
	
	public void delete(Long id) {
		tokenMap.remove(tokenIdMap.get(id));
		tokenMap.remove(id);
	}
	
}
