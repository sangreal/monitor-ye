package enn.monitor.config.service.tablectl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.config.MongoConfig;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
	
public class EnnMonitorConfigServiceTableCtl extends MongoDBTableInstance<EnnMonitorConfigServiceTable> {

	public EnnMonitorConfigServiceTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorConfigServiceTable convertorFromDBObject(DBObject dbObject) throws Exception {
		List<String> guestList = null;
		
		EnnMonitorConfigServiceTable.Builder serviceNameTable = EnnMonitorConfigServiceTable.newBuilder();
		
		serviceNameTable.setId((Long) dbObject.get(EnnMonitorConfigServiceTableField.IdFieldName));
		serviceNameTable.setSeqNo((Long) dbObject.get(EnnMonitorConfigServiceTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorConfigServiceTableField.ServiceNameFieldName) != null) {
			serviceNameTable.setServiceName((String)dbObject.get(EnnMonitorConfigServiceTableField.ServiceNameFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName) != null) {
			serviceNameTable.setBelongToServiceLine((Long) dbObject.get(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.TokenFieldName) != null) {
			serviceNameTable.setToken((String)dbObject.get(EnnMonitorConfigServiceTableField.TokenFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.StatusFieldName) != null) {
			serviceNameTable.setStatus(EnnMonitorConfigServiceStatus.valueOf((String)dbObject.get(EnnMonitorConfigServiceTableField.StatusFieldName)));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.CreateTimeFieldName) != null) {
			serviceNameTable.setCreateTime((Long) dbObject.get(EnnMonitorConfigServiceTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.LastUpdateTimeFieldName) != null) {
			serviceNameTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorConfigServiceTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.OwnerFieldName) != null) {
			serviceNameTable.setOwner((String)dbObject.get(EnnMonitorConfigServiceTableField.OwnerFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceTableField.GuestFieldName) != null) {
			guestList = (List<String>)dbObject.get(EnnMonitorConfigServiceTableField.GuestFieldName);
			if (guestList != null && guestList.size() > 0) {
				serviceNameTable.addAllGuest(guestList);
			}
		}
		
		return serviceNameTable.build();
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorConfigServiceTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		List<String> guestList = null;
		
		dbObject.put(EnnMonitorConfigServiceTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() > 0) {
			dbObject.put(EnnMonitorConfigServiceTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getServiceName() != null && instance.getServiceName().equals("") == false) {
			dbObject.put(EnnMonitorConfigServiceTableField.ServiceNameFieldName, instance.getServiceName());
		}
		
		if (instance.getBelongToServiceLine() > 0) {
			dbObject.put(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName, instance.getBelongToServiceLine());
		}
		
		if (instance.getToken() != null && instance.getToken().equals("") == false) {
			dbObject.put(EnnMonitorConfigServiceTableField.TokenFieldName, instance.getToken());
		}
		
		if (instance.getStatus().equals(EnnMonitorConfigServiceStatus.ServiceDefault) == false) {
			dbObject.put(EnnMonitorConfigServiceTableField.StatusFieldName, instance.getStatus().name());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorConfigServiceTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorConfigServiceTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		if (instance.getOwner() != null && instance.getOwner().equals("") == false) {
			dbObject.put(EnnMonitorConfigServiceTableField.OwnerFieldName, instance.getOwner());
		}
		
		if (instance.getGuestList() != null && instance.getGuestCount() > 0) {
			guestList = new ArrayList<String>(instance.getGuestList());
			dbObject.put(EnnMonitorConfigServiceTableField.GuestFieldName, guestList);
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getServiceTable();
	}
	
	public MongoDBCtrl<EnnMonitorConfigServiceTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
