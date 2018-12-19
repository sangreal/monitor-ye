package enn.monitor.log.config.template.pipe;

import java.util.List;

import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedData;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;

public abstract class EnnMonitorLogConfigTemplatePipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorLogConfigTemplateClient templateClient = null;
	
	public EnnMonitorLogConfigTemplatePipeClientImpl(EnnMonitorLogConfigTemplateClient templateClient) {
		this.templateClient = templateClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return templateClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorLogConfigTemplateTable templateTable = (EnnMonitorLogConfigTemplateTable) object;
		return templateTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigTemplateTable templateTable = (EnnMonitorLogConfigTemplateTable) object;
		return templateTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return templateClient.getValidDataList(startSeqNo, batch);
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorLogConfigTemplateGetDeletedData deletedData = (EnnMonitorLogConfigTemplateGetDeletedData) object;
		return deletedData.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigTemplateGetDeletedData deletedData = (EnnMonitorLogConfigTemplateGetDeletedData) object;
		return deletedData.getSeqNo();
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return templateClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
