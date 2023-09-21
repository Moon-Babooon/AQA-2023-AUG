package org.prog.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DataHolder {

    private static final HashMap<String, Object> holder = new HashMap<>();

    public void put(String key, Object value) {
        holder.put(key, value);
    }

    public Object get(String key) {
        return holder.get(key);
    }

    public static void reset() {
        holder.clear();
    }
}
