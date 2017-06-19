package main.utils;

import java.util.TreeMap;

public class StatMap<K,V> extends TreeMap {
    public void putInkKey(K key){

        if(!this.containsKey(key)){
            this.put(key, 1);
        }
        else {
            int counter = (int) this.get(key);
            this.put(key, counter+1);
        }
    }
}
