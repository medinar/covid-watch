package com.medinar.covidwatch.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rommel Medina
 */
public enum Continent {
    
    ANTARCTICA(0, "Antarctica"),
    AFRICA(1, "Africa"),
    ASIA(2, "Asia"),
    AUSTRALIA_OCEANIA(3, "Australia-Oceania"),
    EUROPE(4, "Europe"),
    NORTH_AMERICA(5, "North America"),
    SOUTH_AMERICA(6, "South America");

    private int code;
    private String value;
    
    private static final Map<Integer, Continent> codeMap = new HashMap<>();
        private Continent(int code, String value) {
        this.code = code;
        this.value = value;
    }
        
    // Caching the Lookup Values
    static {
        for (Continent continent : values()) {
            codeMap.put(continent.code, continent);
        }
    }

    public static Continent get(int code) {
        return codeMap.get(code);
    }
     
    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Continents{" + "code=" + code + ", value=" + value + '}';
    }

}
