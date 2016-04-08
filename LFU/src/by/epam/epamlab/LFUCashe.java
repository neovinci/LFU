package by.epam.epamlab;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LFUCashe {
	private int maxSize;
	private double evictionFactor;
	private Map<String, Integer> cashe;
	private Queue<String>[] frequency;
	
	@SuppressWarnings("unchecked")
	public LFUCashe(int maxSize, double evictionFactor) {
		this.maxSize = maxSize;
		this.evictionFactor = evictionFactor;
		cashe = new HashMap<String, Integer>();
		frequency = new LinkedList[maxSize + 1];
		
		for(int i = 0; i <= maxSize; i++) {
			frequency[i] = new LinkedList<String>();
		}
	}
	
	
	
	public boolean add(String data) {
		boolean isAdded = true;
		
		if(!isFull()) {
			isAdded = addToCashe(data);
		} else if(!cashe.containsKey(data)){
			eviction();
			isAdded = addToCashe(data);
		} else {
			isAdded = false;
		}
		return isAdded;
	}
	
	public String get(String data) {
		String currentDate = cashe.containsKey(data) ? data : null;
		
		if(currentDate != null) {
			int currentFrequency = cashe.get(currentDate);
			
			if(currentFrequency < maxSize) {
				frequency[currentFrequency].remove(currentDate);
				currentFrequency++;
				cashe.put(currentDate, currentFrequency);
				frequency[currentFrequency].add(currentDate);
			} else if(currentFrequency == maxSize) {
				frequency[currentFrequency].remove(currentDate);
				frequency[currentFrequency].add(currentDate);
			}			
		}		
		return currentDate;
	}
	
	private boolean addToCashe(String data) {
		boolean isAdded = true;
		
		if(!cashe.containsKey(data)) {
			cashe.put(data, 0);
			frequency[0].add(data);
		} else {
			isAdded = false;
		}
		return isAdded;
	}
	
	private boolean isFull() {
		return cashe.size() == maxSize ? true : false;
	}
	
	private void eviction() {
		int minFrequency = 0;
		int evictionNumber = (int) Math.round(maxSize * evictionFactor);
		for(int i = 0; i < evictionNumber; i++) {
			for(int n = minFrequency; n <= maxSize; n++) {
				if(cashe.containsValue(n)) {
					minFrequency = n;
					break;
				}
			}
			cashe.remove(frequency[minFrequency].poll());
		}		
	}
	
	public void getCashe() {
		
		for(int i = 0; i < frequency.length; i++) {
			System.out.printf("freq ¹ %d:", i);
			for(String str : frequency[i]) {
				System.out.printf("  %s(%d)", str, cashe.get(str));
			}
			System.out.println();
		}
	}
 }

