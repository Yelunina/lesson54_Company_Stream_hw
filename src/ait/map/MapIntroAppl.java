package ait.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapIntroAppl {
    public static void main(String[] args) {
        Map<String, Integer> cities = new TreeMap<>();
        System.out.println(cities.isEmpty());
        System.out.println(cities.size());
        cities.put("Denver", 600_000);
        cities.put("Boston", 670_000);
        cities.put("Chicago", 2_700_000);
        cities.put("Atlanta", 470_000);
        cities.put("New York", 8_500_000);
        cities.put("Dallas", 1_300_000);
        System.out.println(cities.isEmpty());
        System.out.println(cities.size());
        Integer population = cities.get("Chicago");
        System.out.println(population);
        population = cities.get("Detroit");
        System.out.println(population);

        System.out.println(cities.containsKey("Boston"));
        System.out.println(cities.containsKey("Detroit"));
        System.out.println(cities.containsValue(600_000));
        System.out.println(cities.containsValue(700_000));

        population = cities.put("Chicago", 2_700_001);
        System.out.println(population);
        System.out.println(cities.get("Chicago"));
        Collection<Integer> populations = cities.values();
        int total = 0;
        for (Integer num : populations) {
            total += num;
        }
        System.out.println("Total = " + total);
        System.out.println("================================");
        // method 1 for HashMap O(n) for TreeMap O(n * log(n))
        Set<String> keys = cities.keySet();
        for (String key : keys) {
            System.out.println(key + " -> " + cities.get(key));
        }
        // method 2  for HashMap O(n) for TreeMap O(n)
        Set<Map.Entry<String, Integer>> entries = cities.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}