package enn.monitor.ai.nn.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NNTrainData {
	
	private final static double InputVectors[][] = {
		{1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0},                                 //right
		{-1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0},                     //left
		{0,1, 0,1, 0,1, 0,1, 0,1, 0,1, 0,1, 0,1, 0,1, 0,1, 0,1, 0,1},                                 //down
		{0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1},                     //up
		{1,0, 1,0, 1,0, 0,1, 0,1, 0,1, -1,0, -1,0, -1,0, 0,-1, 0,-1, 0,-1},                           //clockwise square
		{-1,0, -1,0, -1,0, 0,1, 0,1, 0,1, 1,0, 1,0, 1,0, 0,-1, 0,-1, 0,-1},                           //anticlockwise square
		{1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, -0.45,0.9, -0.9, 0.45, -0.9,0.45},              //Right Arrow 
		{-1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, -1,0, 0.45,0.9, 0.9, 0.45, 0.9,0.45},        //Left Arrow
		{-0.7,0.7, -0.7,0.7, -0.7,0.7, -0.7,0.7, -0.7,0.7, -0.7,0.7, -0.7,0.7, -0.7,0.7,             //south west
			-0.7, 0.7, -0.7,0.7, -0.7,0.7, -0.7,0.7},
		{0.7,0.7, 0.7,0.7, 0.7,0.7, 0.7,0.7, 0.7,0.7, 0.7,0.7, 0.7,0.7, 0.7,0.7, 0.7,0.7,            //south east
			0.7,0.7, 0.7,0.7, 0.7,0.7},    
		{1,0, 1,0, 1,0, 1,0, -0.72,0.69,-0.7,0.72,0.59,0.81, 1,0, 1,0, 1,0, 1,0, 1,0}                 //zorro
	}; 

	private final static String Names[] = {
		"Right",
		"Left",
		"Down",
		"Up",
		"Clockwise Square",
		"Anti-Clockwise Square",
		"Right Arrow",
		"Left Arrow",
		"South West",
		"South East",
		"Zorro"
	};
	
	private List<List<Double>>  inputListList = new ArrayList<List<Double>>();
	private List<String> namesList = null;
	
	private Map<String, Integer> namePosMap = new HashMap<String, Integer>();
	private Map<Integer, Integer> inputOutputMap = new HashMap<Integer, Integer>();
	
	public NNTrainData() {
		init();
	}
	
	public void addTrainData(String name, List<Double> dataList) {
		if (namePosMap.containsKey(name) == true) {
			inputListList.add(dataList);
			inputOutputMap.put(inputListList.size() - 1, namePosMap.get(name));
		} else {
			inputListList.add(dataList);
			namesList.add(name);
			
			namePosMap.put(name, namesList.size() - 1);
			inputOutputMap.put(inputListList.size() - 1, namePosMap.get(name));
		}
	}
	
	public void clear() {
		init();
	}
	
	public int getInputSize() {
		return inputListList.get(0).size();
	}
	
	public int getOutputSize() {
		return namesList.size();
	}
	
	public String getNames(int index) {
		if (index >= namesList.size()) {
			return "null";
		}
		
		return namesList.get(index);
	}
	
	public List<List<Double>> getInputListList() {
		return inputListList;
	}
	
	public List<List<Double>> getOutputList() {
		int i, j;
		
		List<Double> outputList = null;
		List<List<Double>> outputListList = new ArrayList<List<Double>>();
		
		for (i = 0; i < inputListList.size(); ++i) {
			outputList = new ArrayList<Double>();
			for (j = 0; j < namesList.size(); ++j) {
				outputList.add(0.0);
			}
			outputList.set(inputOutputMap.get(i), 1.0);
			outputListList.add(outputList);
		}
		
		return outputListList;
	}
	
	private void init() {
		int i, j;
		List<Double> list = null;
		
		for (i = 0; i < InputVectors.length; ++i) {
			list = new ArrayList<Double>();
			for (j = 0; j < InputVectors[i].length; ++j) {
				list.add(InputVectors[i][j]);
			}
			inputListList.add(list);
			
			inputOutputMap.put(i, i);
			namePosMap.put(Names[i], i);
		}
		
		namesList = Arrays.asList(Names);
	}
}
