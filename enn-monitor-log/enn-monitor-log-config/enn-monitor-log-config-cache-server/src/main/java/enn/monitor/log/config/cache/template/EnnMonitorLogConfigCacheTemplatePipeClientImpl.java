package enn.monitor.log.config.cache.template;

import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedData;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;
import enn.monitor.log.config.template.pipe.EnnMonitorLogConfigTemplatePipeClientImpl;

public class EnnMonitorLogConfigCacheTemplatePipeClientImpl extends EnnMonitorLogConfigTemplatePipeClientImpl {
	
	public EnnMonitorLogConfigCacheTemplatePipeClientImpl(EnnMonitorLogConfigTemplateClient templateClient) {
		super(templateClient);
	}

	@Override
	protected void updateAndInsert(Object object) {
		EnnMonitorLogConfigTemplateTable templateTable = (EnnMonitorLogConfigTemplateTable) object;
		EnnMonitorLogConfigCacheTemplateUtil.add(templateTable.getId(), templateTable.getTemplateKey(), templateTable.getTagId());
	}

	@Override
	protected void delete(Object object) {
		EnnMonitorLogConfigTemplateGetDeletedData deletedTable = (EnnMonitorLogConfigTemplateGetDeletedData) object;
		EnnMonitorLogConfigCacheTemplateUtil.delete(deletedTable.getId());
	}
	
}
