package codeaggressive.com.bctrace;

import java.util.HashMap;

class SkuFromBatch {
    private final static HashMap<String, String> batchHashMap = new HashMap<>();

    private static HashMap<String, String> getBatchHashMap() {
        return batchHashMap;
    }

    private static void setBatchHashMap() {
        HashMap<String, String> batchHashMap = getBatchHashMap();
        batchHashMap.put("a", "Knorr Chicken");
        batchHashMap.put("b", "Knorr Beef");
        batchHashMap.put("c", "Royco Beef");
        batchHashMap.put("d", "Royco Chicken");
        batchHashMap.put("e", "Royco Goat");
        batchHashMap.put("f", "Royco Stew");
        batchHashMap.put("g", "Knorr Chicken Stomp");
        batchHashMap.put("h", "Knorr Beef Stomp");
        batchHashMap.put("i", "Royco Chicken Stomp");
        batchHashMap.put("j", "Royco Stew Stomp");
    }

    public static String getSku(String batchNo) {
        setBatchHashMap();
        String batchKey = batchNo.toLowerCase().substring(6, 7);
        return getBatchHashMap().get(batchKey);
    }
}