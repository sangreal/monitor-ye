package enn.monitor.log.train.common.nn.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import enn.monitor.log.train.common.util.EnnMonitorLogTrainCommonUtil;

public class TrainNNData {
	
	private Map<Integer, Integer> inputOutputMap = new HashMap<Integer, Integer>();
	
	private int count = 0;
	private Map<String, Integer> keysMap = new HashMap<String, Integer>();
	private List<List<Integer>>  keysListList = new ArrayList<List<Integer>>();
	
	private List<String> resultsList = new ArrayList<String>();
	private Map<String, Integer> resultsPosMap = new HashMap<String, Integer>();
	
	public TrainNNData() {
	}
	
	// 切词语需要改成跟formatInputList的一样
	public void addTrainData(String tag, String keys) {
		int i;
		String[] words = keys.split("\\s+");
		
		if (resultsPosMap.containsKey(tag) == false) {
			resultsList.add(tag);
			resultsPosMap.put(tag, resultsList.size() - 1);
		}
		
		keysListList.add(new ArrayList<Integer>());
		for (i = 0; i < words.length; ++i) {
			if (words[i].trim() == null || words[i].trim().equals("") == true) {
				continue;
			}
			
			if (keysMap.containsKey(words[i].toLowerCase()) == false) {
				keysMap.put(words[i].toLowerCase(), count++);
			}
			keysListList.get(keysListList.size() - 1).add(keysMap.get(words[i].toLowerCase()));
		}
		
		inputOutputMap.put(keysListList.size() - 1, resultsPosMap.get(tag));
	}
	
	public void clear() {
		inputOutputMap.clear();
		
		count = 0;
		keysMap.clear();
		keysListList.clear();
		
		resultsList.clear();
		resultsPosMap.clear();
	}
	
	public int getInputSize() {
		return keysMap.size();
	}
	
	public int getOutputSize() {
		return resultsList.size();
	}
	
	public List<Double> formatInputList(String data) throws Exception {
		int i;
		Integer pos;
		List<Double> inputList = new ArrayList<Double>();
		
		Set<String> wordSet = null;
		
		if (data == null) {
			return inputList;
		}
		
		for (i = 0; i < getInputSize(); ++i) {
			inputList.add(0.0);
		}
		
		wordSet = EnnMonitorLogTrainCommonUtil.convertToWords(data);
		for (String word : wordSet) {
			pos = keysMap.get(word.toLowerCase());
			if (pos != null) {
				inputList.set(pos, 1.0);
			}
		}
		
		return inputList;
	}
	
	public String getResults(int index) {
		if (index >= resultsList.size()) {
			return "null";
		}
		
		return resultsList.get(index);
	}
	
	public long getKeysPos(String key) {
		return keysMap.get(key);
	}
	
	public List<List<Double>> getInputListList() {
		int i, j;
		List<Integer> keysList = null;
		List<List<Double>> inputListList = new ArrayList<List<Double>>();
		
		for (i = 0; i < keysListList.size(); ++i) {
			keysList = keysListList.get(i);
			inputListList.add(new ArrayList<Double>());
			for (j = 0; j < getInputSize(); ++j) {
				inputListList.get(inputListList.size() - 1).add(0.0);
			}
			
			for (j = 0; j < keysList.size(); ++j) {
				inputListList.get(inputListList.size() - 1).set(keysList.get(j), 1.0);
			}
		}
		
		return inputListList;
	}
	
	public List<List<Double>> getOutputList() {
		int i, j;
		
		List<Double> outputList = null;
		List<List<Double>> outputListList = new ArrayList<List<Double>>();
		
		for (i = 0; i < keysListList.size(); ++i) {
			outputList = new ArrayList<Double>();
			for (j = 0; j < resultsList.size(); ++j) {
				outputList.add(0.0);
			}
			outputList.set(inputOutputMap.get(i), 1.0);
			outputListList.add(outputList);
		}
		
		return outputListList;
	}
	
	public static void main(String[] args) throws Exception {
		//String data = "Sync \"dataflow-demo-ye/pre1-resourcemanager1-3483819581\" failed with unable to create pods: pods \"pre1-resourcemanager1-3483819581-\" is forbidden: minimum memory usage per Container is 1G, but request is 500M.";
		//String data = "Operation for \"delete-pvc-c284b565-076b-11e8-93c3-244427a00032[f9e52c59-076b-11e8-93c3-244427a00032]\" failed. No retries permitted until 2018-02-28 11:24:01.982842757 +0000 UTC m=+1418870.536171077 (durationBeforeRetry 2m0s). Error: rbd kubernetes-dynamic-pvc-f9c9e8ff-076b-11e8-ad06-244427a00032 is still being used";
		//String data = "E0228 19:26:49.267241   35761 nestedpendingoperations.go:262] Operation=for \"\\\"kubernetes.io/cephfs/c4337cc7-000d-11e8-a017-244427a00052-lbsheng-pre1-zkutils-pv\\\" (\\\"c4337cc7-000d-11e8-a017-244427a00052\\\")\" failed. No retries permitted until 2018-02-28 19:28:49.267208371 +0800 CST (durationBeforeRetry 2m0s). Error: UnmountVolume.TearDown failed for volume \"kubernetes.io/cephfs/c4337cc7-000d-11e8-a017-244427a00052-lbsheng-pre1-zkutils-pv\" (volume.spec.Name: \"pre1-zkutils\") pod \"c4337cc7-000d-11e8-a017-244427a00052\" (UID: \"c4337cc7-000d-11e8-a017-244427a00052\") with: Error checking if path exists: stat /data/kubelet/pods/c4337cc7-000d-11e8-a017-244427a00052/volumes/kubernetes.io~cephfs/lbsheng-pre1-zkutils-pv: stale NFS file handle";
//		String data = "ConnectionManager: [id: 0x31e4e923, /172.16.126.34:41652 =\u003e /172.16.100.60:4242] OPEN\\n";
		//String[] datas = data.split("[\\s+\"-/:\\[\\]=\\\\]+");
		String data = "";
		
		Set<String> words = EnnMonitorLogTrainCommonUtil.convertToWords(data);
		
		for (String item : words) {
			System.out.println(item);
		}
	}
	
}
