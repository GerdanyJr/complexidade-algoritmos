package br.edu.ifba.redesMoveis.port.util;

import java.util.Map;
import java.util.TreeMap;

public class Util {
    public static Map<String, Integer> getDayHours() {
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < 24; i++) {
            String hour = String.format("%02d:00", i);
            map.put(hour, 0);
        }
        return map;
    }
}
