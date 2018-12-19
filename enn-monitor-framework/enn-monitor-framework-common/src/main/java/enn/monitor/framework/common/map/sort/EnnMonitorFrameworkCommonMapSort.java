package enn.monitor.framework.common.map.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class EnnMonitorFrameworkCommonMapSort {
	
	public static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortByValue(Map<K, V> map, boolean asc) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            	if (asc) {
            		return (o1.getValue()).compareTo(o2.getValue());
            	} else {
            		return (o2.getValue()).compareTo(o1.getValue());
            	}
            }
        });

        return list;
    }
	
	public static <K extends Comparable<? super K>, V> List<Map.Entry<K, V>> sortByKey(Map<K, V> map, boolean asc) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            	if (asc) {
            		return (o1.getKey()).compareTo(o2.getKey());
            	} else {
            		return (o2.getKey()).compareTo(o1.getKey());
            	}
            }
        });

        return list;
    }

}
