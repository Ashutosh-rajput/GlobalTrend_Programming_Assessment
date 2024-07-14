import java.util.HashMap;
import java.util.LinkedHashSet;

public class LRUCache {
    private final int capacity;
    private final HashMap<Integer, Integer> cache;
    private final LinkedHashSet<Integer> usage;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.usage = new LinkedHashSet<>();
    }
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        usage.remove(key);
        usage.add(key);
        return cache.get(key);
    }
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            usage.remove(key);
        } else if (cache.size() >= capacity) {
            int lruKey = usage.iterator().next();
            usage.remove(lruKey);
            cache.remove(lruKey);
        }
        cache.put(key, value);
        usage.add(key);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 10);
        lruCache.put(2, 20);
        lruCache.put(3, 30);

        // Test get
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(4));

        // Test put operation which causes eviction
        lruCache.put(4, 40);

        // After putting 4, so key 3 should be deleted
        System.out.println(lruCache.get(3)); // return -1
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(4));


    }
}