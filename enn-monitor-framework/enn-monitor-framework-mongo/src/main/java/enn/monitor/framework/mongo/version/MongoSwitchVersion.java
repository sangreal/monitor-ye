package enn.monitor.framework.mongo.version;

import java.util.List;

import com.mongodb.BasicDBObject;

public abstract class MongoSwitchVersion {
    private MongoVersionTableCtl versionCtrl = null;
    
    private String table = null;

    public MongoSwitchVersion(String mongoUrl, String dbName, String table) throws Exception {
        if (mongoUrl == null || dbName == null || table == null) {
            throw new Exception("in MongoSwitchVersion, the argument is null");
        }
        
        this.table = table;
        versionCtrl = new MongoVersionTableCtl(mongoUrl, dbName, table);
    }

    // true : 版本验证通过
    // false : 需要重建索引
    synchronized private boolean versionValidate() throws Exception {
        // 版本验证
    	MongoVersionTable versionTable = null;
        List<MongoVersionTable> versionTableList = null;

        versionTableList = getVersionTableList();
        if (versionTableList.size() <= 0) {
            return false;
        } else if (versionTableList.size() > 1) {
            throw new Exception("The VersionTable is more than one record");
        }

        versionTable = versionTableList.get(0);
        if ((versionTable.getVersionNo() <= 0)) {
            throw new Exception("The version is null");
        }
        
        if (getVersion() != versionTable.getVersionNo()) {
            return false;
        }

        return true;
    }
    
    synchronized private void versionUpdate() throws Exception {
        BasicDBObject query = null;
        MongoVersionTable versionTable = null;
        List<MongoVersionTable> versionTableList = null;

        versionTableList = getVersionTableList();
        if (versionTableList.size() <= 0) {
            versionTable = new MongoVersionTable();
            versionTable.setTableName(table);
            versionTable.setVersionNo(getVersion());

            versionCtrl.getMongoDBCtrl().insert(versionTable, true);
            return;
        } else if (versionTableList.size() > 1) {
            throw new Exception("The VersionTable is more than one record");
        }

        versionTable = versionTableList.get(0);
        if ((versionTable.getVersionNo() <= 0)) {
            throw new Exception("The version is null");
        }

        if (getVersion() != versionTable.getVersionNo()) {
            query = new BasicDBObject();
            query.put(MongoVersionTable.TableNameFieldName, versionTable.getTableName());
            versionTable.setVersionNo(getVersion());
            versionCtrl.getMongoDBCtrl().update(query, versionTable, false, true, true);
        }
    }

    private List<MongoVersionTable> getVersionTableList() throws Exception {
        List<MongoVersionTable> versionTableList = versionCtrl.getMongoDBCtrl().searchAllData(null, null);
        return versionTableList;
    }

    public void initProject() throws Exception {
        if (versionValidate() == false) {
            switchVersion();
            versionUpdate();
        }
    }
    
    abstract public void switchVersion()  throws Exception;
    abstract public long getVersion();
}
