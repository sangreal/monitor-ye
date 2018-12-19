package enn.monitor.framework.log.proto;

import java.util.Map;

public class ESLog {

	private String dateTime = null;                    // date 2015-01-01T12:10:30.002+0800
	private long createTime = 0l;                      // date

	// TRACE DEBUG INFO WARN ERROR FATAL
	private String logLevel = null;                    // KeyWord

	private String logType = null;                     // KeyWord

	private String clusterName = null;                 // KeyWord
	private String nodeName = null;                    // ip

	private String appName = null;                     // Text
	private String nameSpace = null;                   // KeyWord
	private String podName = null;                     // Text

	private String position = null;                    // Text

	private String pid = null;                         // Text
	private String threadName = null;                  // Text

	private String traceId = null;                     // KeyWord
	private String parentPod = null;                   // Text
	private String bizLine = null;                   // Text
	private String service = null;                   // Text
	private String resource = null;                   // Text

	private String instance = null;                   // Text

	private String log = null;                         // Text

	private String token = null;                       // Text
	
	// analyse log, result
	private String templateKey = null;
	private String tag = null;
	private Double match = null;
	
	public void parse(Map<String, String> logMap) {
		if (logMap == null || logMap.size() <= 0) {
			return;
		}
		
		for (Map.Entry<String, String> entry : logMap.entrySet()) {
			switch (entry.getKey()) {
			case "dateTime":
				if (entry.getValue() != null) {
					this.dateTime = entry.getValue();
				}
				break;
			case "createTime":
				if (entry.getValue() != null) {
					this.createTime = Long.parseLong(entry.getValue());
				}
				break;
			case "logLevel":
				if (entry.getValue() != null) {
					this.logLevel = entry.getValue();
				}
				break;
			case "logType":
				if (entry.getValue() != null) {
					this.logType = entry.getValue();
				}
				break;
			case "clusterName":
				if (entry.getValue() != null) {
					this.clusterName = entry.getValue();
				}
				break;
			case "nodeName":
				if (entry.getValue() != null) {
					this.nodeName = entry.getValue();
				}
				break;
			case "appName":
				if (entry.getValue() != null) {
					this.appName = entry.getValue();
				}
				break;
			case "nameSpace":
				if (entry.getValue() != null) {
					this.nameSpace = entry.getValue();
				}
				break;
			case "podName":
				if (entry.getValue() != null) {
					this.podName = entry.getValue();
				}
				break;
			case "position":
				if (entry.getValue() != null) {
					this.position = entry.getValue();
				}
				break;
			case "pid":
				if (entry.getValue() != null) {
					this.pid = entry.getValue();
				}
				break;
			case "threadName":
				if (entry.getValue() != null) {
					this.threadName = entry.getValue();
				}
				break;
			case "traceId":
				if (entry.getValue() != null) {
					this.traceId = entry.getValue();
				}
				break;
			case "parentPod":
				if (entry.getValue() != null) {
					this.parentPod = entry.getValue();
				}
				break;
			case "bizLine":
				if (entry.getValue() != null) {
					this.bizLine = entry.getValue();
				}
				break;
			case "service":
				if (entry.getValue() != null) {
					this.service = entry.getValue();
				}
				break;
			case "resource":
				if (entry.getValue() != null) {
					this.resource = entry.getValue();
				}
				break;
			case "instance":
				if (entry.getValue() != null) {
					this.instance = entry.getValue();
				}
				break;
			case "log":
				if (entry.getValue() != null) {
					this.log = entry.getValue();
				}
				break;
			case "token":
				if (entry.getValue() != null) {
					this.token = entry.getValue();
				}
				break;
			}
		}
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getPodName() {
		return podName;
	}

	public void setPodName(String podName) {
		this.podName = podName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getParentPod() {
		return parentPod;
	}

	public void setParentPod(String parentPod) {
		this.parentPod = parentPod;
	}
	
	public String getBizLine() {
		return bizLine;
	}

	public String getService() {
		return service;
	}

	public String getResource() {
		return resource;
	}

	public String getInstance() {
		return instance;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Double getMatch() {
		return match;
	}

	public void setMatch(Double match) {
		this.match = match;
	}
	
}
