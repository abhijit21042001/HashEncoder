package com.mrunali;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonKeyFinder {
	
    public static String findKeyInJsonFile(String filePath, String keyToFind) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            JSONTokener tokener = new JSONTokener(br);
            Object json = tokener.nextValue();
            return findKey(json, keyToFind);
        }
    }

    private static String findKey(Object json, String keyToFind) {
        if (json instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) json;
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonObject.get(key);

                if (key.equals(keyToFind)) {
                    return value.toString();
                } else {
                    // Recurse if the value is a JSONObject or JSONArray
                    String result = findKey(value, keyToFind);
                    if (result != null) {
                        return result;
                    }
                }
            }
        } else if (json instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) json;

            for (int i = 0; i < jsonArray.length(); i++) {
                Object value = jsonArray.get(i);
                // Recurse into each item in the array
                String result = findKey(value, keyToFind);
                if (result != null) {
                    return result;
                }
            }
        }
        // Return null if the key is not found at this level
        return null;
    }
}
