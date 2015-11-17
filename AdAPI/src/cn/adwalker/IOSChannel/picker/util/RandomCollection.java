package cn.adwalker.IOSChannel.picker.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * @author 加权随机显示广告
 *
 * @param <E>
 */
public class RandomCollection<E> {
    private NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;
    public double getTotal(){
    	return total;
    }
    
    public boolean isEmpty(){
    	return map.isEmpty();
    }
    public void info(){
    	System.out.println(map.size()+"  "+total);
    }
    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public void add(double weight, E result) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }
    
    public void clear() {
        map.clear();
    }

    public E next() {
        double value = random.nextDouble() * total;
        if(!map.isEmpty())
        	return map.ceilingEntry(value).getValue();
        else
        	return null;
    }
    public E removeNext(){
    	E e=next();
    	remap(e);
    	return e;
    }
    
    public void remap(E result){

        if(!map.isEmpty()) {
        	if(map.containsValue(result)) {         		
        		double total_x = 0;
        		double remove = 0;
        		double prev = 0;
        		NavigableMap<Double, E> tmp = new TreeMap<Double, E>();
        		for (Iterator<Entry<Double, E>> it = map.entrySet().iterator(); it.hasNext();) {
        			Entry<Double, E> entry = it.next();
        			if (entry.getValue().equals(result)) {
        				remove = entry.getKey();
        			} else { 
        				if (0 == remove) {
        					prev = entry.getKey();
        					total_x = prev;
        				} else {
        					total_x = prev + entry.getKey() - remove;
        				}
        				tmp.put(total_x, entry.getValue());	
        			}
        			
        		}
        		map.clear();
    			map.putAll(tmp);
    			total=total_x;
        	}
        }
    }
    
    public E next(E result) {
        if(!map.isEmpty()) {
        	if(map.containsValue(result)) {         		
        		double total_x = 0;
        		double remove = 0;
        		double prev = 0;
        		NavigableMap<Double, E> tmp = new TreeMap<Double, E>();
        		for (Iterator<Entry<Double, E>> it = map.entrySet().iterator(); it.hasNext();) {
        			Entry<Double, E> entry = it.next();
        			
        			if (entry.getValue().equals(result)) {
        				remove = entry.getKey();
        			} else { 
        				if (0 == remove) {
        					prev = entry.getKey();
        					total_x = prev;
        				} else {
        					total_x = prev + entry.getKey() - remove;
        				}
        				tmp.put(total_x, entry.getValue());
        			}
        		}
        		double value = random.nextDouble() * total_x;
        		if (!tmp.isEmpty()) {
        			return tmp.ceilingEntry(value).getValue();
        		}
        	} else {
        		double value = random.nextDouble() * total;
        		return map.ceilingEntry(value).getValue();
        	}
        }
        
        return null;
    }
    
    public int size() {
        return map.size();
    }
    public List<E> getList(){
    	List<E> list = new ArrayList<E>();
        for(Entry<Double, E> e:map.entrySet()){
        	list.add(e.getValue());
        }
        return list;
    }

}
