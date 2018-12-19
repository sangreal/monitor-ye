package enn.monitor.framework.lang.format.aggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.framework.lang.format.common.LangFormatLogImpl;

public class LangFormatAggregatorFSM extends LangFormatLogImpl {
	
	private static final Logger logger = LogManager.getLogger();
	
	private List<LangFormatAggregatorDataGroup> dataGroupList = new ArrayList<LangFormatAggregatorDataGroup>();
	
	private LangFormatAggregatorDataItem dataItem = null;
	private LangFormatAggregatorDataGroup dataGroup = null;
	
	public void parse(String format) throws Exception {
		int index = 0;
		int status = 0;
		
		StringBuilder key = new StringBuilder();
		StringBuilder data = new StringBuilder();
		
		StringBuilder fkey = new StringBuilder();
		StringBuilder fvalue = new StringBuilder();
		
		if (format == null || format.equals("") == true) {
			throw new Exception("the format is null");
		}
		
		while (true) {
			switch (status) {
			case 0:
				switch (format.charAt(index)) {
				case '<':
					status = 1;
					break;
				case '%':
					status = 3;
					break;
				case '$':
					status = 6;
					break;
				default:
					data.append(format.charAt(index));
					status = 2;
					break;
				}
				break;
			case 1:
				switch (format.charAt(index)) {
				case '>':
					status = 2;
					break;
				default:
					key.append(format.charAt(index));
					break;
				}
				break;
			case 2:
				if (index >= format.length()) {
					addValue(data);
					addDataGroup(key, data);
					
					return;
				}
				
				switch (format.charAt(index)) {
				case '%':
					addValue(data);
					status = 3;
					break;
				case '$':
					addValue(data);
					status = 6;
					break;
				case ',':
					addValue(data);
					addDataGroup(key, data);

					status = 0;
					break;
				default:
					data.append(format.charAt(index));
					break;
				}
				break;
			case 3:
				switch (format.charAt(index)) {
				case '%':
					if (key.length() == 0) {
						key.append(data.toString());
					}
					
					if (dataItem == null) {
						dataItem = new LangFormatAggregatorDataItem();
					}
					
					dataItem.fieldDataTypeEnum = LangFormatAggregatorFieldTypeEnum.Variable;
					dataItem.value = data.toString();
					
					if (dataGroup == null) {
						dataGroup = new LangFormatAggregatorDataGroup();
					}
					
					dataGroup.dataItemList.add(dataItem);
					dataItem = null;
					data.delete(0, data.length());

					status = 2;
					break;
				case '[':
					status = 4;
					fkey.delete(0, fkey.length());
					fvalue.delete(0, fvalue.length());
					break;
				default:
					data.append(format.charAt(index));
					break;
				}
				break;
			case 4:
				switch(format.charAt(index)) {
				case ':':
					status = 5;
					break;
				default:
					fkey.append(format.charAt(index));
					break;
				}
				break;
			case 5:
				switch (format.charAt(index)) {
				case '/':
				case ']':
					if (dataItem == null) {
						dataItem = new LangFormatAggregatorDataItem();
					}
					
					if (dataItem.hash == null) {
						dataItem.hash = new HashMap<String, String>();
					}
					
					dataItem.hash.put(fkey.toString(), fvalue.toString());
					fkey.delete(0, fkey.length());
					fvalue.delete(0, fvalue.length());
					
					if (format.charAt(index) == '/') {
						status = 4;
					} else {
						status = 3;
					}
					break;
				default:
					fvalue.append(format.charAt(index));
				}
				break;
			case 6:
				switch (format.charAt(index)) {
				case '$':
					if (key.length() == 0) {
						key.append(data.toString());
					}
					
					if (dataItem == null) {
						dataItem = new LangFormatAggregatorDataItem();
					}
					
					dataItem.fieldDataTypeEnum = LangFormatAggregatorFieldTypeEnum.FunctionName;
					dataItem.value = data.toString();
					
					if (dataGroup == null) {
						dataGroup = new LangFormatAggregatorDataGroup();
					}
					dataGroup.dataItemList.add(dataItem);
					
					dataItem = null;
					data.delete(0, data.length());
					
					status = 2;
					break;
				default:
					data.append(format.charAt(index));
					break;
				}
				break;
			}
			++index;
		}
	}
	
	public Map<String, String> aggregator(Map<String, String> input) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		if (input == null || input.size() <= 0) {
			throw new Exception("the input is null");
		}
		
		StringBuilder logData = new StringBuilder();
		String buf = null;
		String bufReplace = null;
		
		for (LangFormatAggregatorDataGroup dataGroup : dataGroupList) {
			logData.delete(0, logData.length());
			
			for (LangFormatAggregatorDataItem dataItem : dataGroup.dataItemList) {
				switch (dataItem.fieldDataTypeEnum) {
				case None:
					return null;
				case Value:
					if (dataItem.value == null) {
						continue;
					}
					
					logData.append(dataItem.value);
					break;
				case Variable:
					if (dataItem.value == null) {
						continue;
					}
					
					buf = input.get(dataItem.value);
					if (buf != null) {
						if (dataItem.hash == null || dataItem.hash.size() <= 0) {
							logData.append(buf);
						} else {
							bufReplace = dataItem.hash.get(buf);
							if (bufReplace != null) {
								logData.append(bufReplace);
							} else {
								logData.append(buf);
							}
						}
					}
					break;
				case FunctionName:
					if (dataItem.value == null) {
						continue;
					}
					
					if (dataItem.value.equals("year") == true) {
						logData.append(EnnDatetimeUtil.getCurrentYear(EnnTimezoneUtil.getChinaTimeZone()));
					} else if (dataItem.value.equals("month") == true) {
						logData.append(EnnDatetimeUtil.getCurrentMonth(EnnTimezoneUtil.getChinaTimeZone()));
					} else if (dataItem.value.equals("day")) {
						logData.append(EnnDatetimeUtil.getCurrentDay(EnnTimezoneUtil.getChinaTimeZone()));
					} else if (dataItem.value.equals("datetime")) {
						logData.append(EnnDatetimeUtil.getCurrentDate(EnnTimezoneUtil.getChinaTimeZone()));
					}
					break;
				default:
					throw new Exception("the unexpected fieldDataTypeEnum, it is " + dataItem.fieldDataTypeEnum.name());
				}
			}
			
			if (logData.length() == 0) {
				continue;
			}
			
			resultMap.put(dataGroup.key, logData.toString());
		}

		return resultMap;
	}
	
	private static class LangFormatAggregatorDataItem extends LangFormatLogImpl {
		public LangFormatAggregatorFieldTypeEnum fieldDataTypeEnum = LangFormatAggregatorFieldTypeEnum.None;

		public String value = null;
		public Map<String, String> hash = null;
		
		@Override
		public void logPrint(int length) {
			switch (fieldDataTypeEnum) {
			case None:
				logger.info("    None is Exception");
				break;
			case Value:
				logger.info("    Value = " + value);
				break;
			case Variable:
				logger.info("    Variable = " + value);
				break;
			case FunctionName:
				logger.info("    FunctionName = " + value);
				break;
			default:
				logger.info("    Exception");
				break;
			}
			
			if (hash != null) {
				for (Map.Entry<String, String> entry : hash.entrySet()) {
					logger.info("        " + entry.getKey() + ":" + entry.getValue());
				}
			}
		}
	};
	
	private static class LangFormatAggregatorDataGroup extends LangFormatLogImpl {
		public String key = null;
		public List<LangFormatAggregatorDataItem> dataItemList = new ArrayList<LangFormatAggregatorDataItem>();
		
		@Override
		public void logPrint(int length) {
			logger.info("Key: " + key);
			for (LangFormatAggregatorDataItem dataItem : dataItemList) {
				dataItem.logPrint(length + 1);
			}
		}
	};


	@Override
	public void logPrint(int length) {
		for (LangFormatAggregatorDataGroup dataGroup : dataGroupList) {
			dataGroup.logPrint(length + 1);
			logger.info("");
		}
	}
	
	private void addValue(StringBuilder data) {
		if (data.length() != 0) {
			if (dataItem == null) {
				dataItem = new LangFormatAggregatorDataItem();
			}
			
			dataItem.fieldDataTypeEnum = LangFormatAggregatorFieldTypeEnum.Value;
			dataItem.value = data.toString();
			
			if (dataGroup == null) {
				dataGroup = new LangFormatAggregatorDataGroup();
			}
			
			dataGroup.dataItemList.add(dataItem);
			dataItem = null;
			data.delete(0, data.length());
		}
	}
	
	private void addDataGroup(StringBuilder key, StringBuilder data) {
		if (dataGroup != null) {
			dataGroup.key = key.toString();
			dataGroupList.add(dataGroup);
			
			dataItem = null;
			dataGroup = null;
			data.delete(0, data.length());
			key.delete(0, key.length());
		}
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder key = new StringBuilder();
		
		String test = null;
		
		key.append("abcdef");
		test = key.toString();
		
		key.append("fsefsefsef");
		
		System.out.println(test);
		System.out.println(key.toString());
		
	}
	
}
