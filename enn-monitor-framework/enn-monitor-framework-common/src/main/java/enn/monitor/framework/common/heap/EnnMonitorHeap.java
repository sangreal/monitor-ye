package enn.monitor.framework.common.heap;

import java.util.ArrayList;
import java.util.List;

public class EnnMonitorHeap {

	private List<EnnMonitorHeapData> heap = new ArrayList<EnnMonitorHeapData>();
	
	public EnnMonitorHeapData getEnnMonitorHeapData() {
		if (heap.size() <= 0) {
			return null;
		}
		
		return heap.get(0);
	}
	
	public void remove() {
		EnnMonitorHeapData heapData = null;
		
		if (heap.size() <= 0) {
			return;
		}
		
		int parent = 0;
		int son = 0;
		
		if (heap.size() == 0) {
			return;
		}
		
		if (heap.size() == 1) {
			heap.remove(0);
			return;
		}
		
		EnnMonitorHeapData data = heap.get(heap.size() - 1);
		heap.remove(heap.size() - 1);
		heap.set(0, data);
		
		parent = 0;
		while (parent < heap.size()) {
			son = parent * 2 + 1;
			if (son >= heap.size()) {
				break;
			}
			if (son + 1 < heap.size()) {
				if (heap.get(son + 1).compare(heap.get(son)) < 0) {
					son = son + 1;
				}
			}
			
			if (heap.get(parent).compare(heap.get(son)) <= 0) {
				break;
			}
			
			heapData = heap.get(parent);
			heap.set(parent, heap.get(son));
			heap.set(son, heapData);
			
			parent = son;
		}
	}
	
	public void add(EnnMonitorHeapData item) {
		int parent = 0;
		int son = 0;
		
		EnnMonitorHeapData heapData = null;
		
		heap.add(item);
		son = heap.size() - 1;
		while (son > 0) {
			parent = (son - 1) / 2;
			if (heap.get(parent).compare(heap.get(son)) < 0) {
				break;
			}
			
			heapData = heap.get(parent);
			heap.set(parent, heap.get(son));
			heap.set(son, heapData);
			
			son = parent;
		}
	}
	
	public int size() {
		return heap.size();
	}
	
	public List<EnnMonitorHeapData> copyDataList() {
		return new ArrayList<EnnMonitorHeapData>(heap);
	}
	
	public void clear() {
		heap.clear();
	}
	
}
