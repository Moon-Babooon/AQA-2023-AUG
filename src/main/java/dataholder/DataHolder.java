package dataholder;

import java.util.HashMap;

public class DataHolder {

    private static final DataHolder instance = new DataHolder();

    private final HashMap<String, Object> holder;

    private DataHolder() {
        this.holder = new HashMap<>();
    }

    public static DataHolder getInstance(){
        return instance;
    }

    public void put(String key, Object value) {
        holder.put(key, value);
    }

    public Object get(String key) {
        return holder.get(key);
    }

    public void reset() {
        holder.clear();
    }

}
