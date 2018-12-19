package enn.monitor.framework.mongo.autoinc;

public class MongoAutoIncTable {
	/*
	 string tablename = 1;
     uint64 id = 2; 
	 */

	public static final String TableNameFieldName = "tableName";
	private String tableName = null;
	
	public static final String IdFieldName = "id";
	private long id = -1;
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
