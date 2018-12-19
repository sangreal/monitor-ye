package enn.monitor.framework.mongo.version;

public class MongoVersionTable {
	
	public static final String TableNameFieldName = "tableName";
	private String tableName = null;
	
	public static final String VersionNoFieldName = "versionNo";
	private long versionNo = -1;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}

}
