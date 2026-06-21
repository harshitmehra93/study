package study.interview.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Design a time-based key-value data structure that supports:
void set(String key, String value, int timestamp)
String get(String key, int timestamp)
Rules:
set stores the key with value at the given timestamp.
get returns the value with the largest timestamp <= timestamp.
If no such timestamp exists, return "".
Timestamps for each key are strictly increasing across set calls.
 */
public class TimeBasedKeyValueStore {
    Map<String, List<ValueTimePair>> map;

    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new ValueTimePair(value, timestamp));
    }

    public String get(String key, int largestTimestamp) {
        if (!map.containsKey(key)) return "";
        List<ValueTimePair> values = map.get(key);

        int low = 0;
        int high = values.size() - 1;

        String answer = "";

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (values.get(mid).timestamp <= largestTimestamp) {
                answer = values.get(mid).value;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    record ValueTimePair(String value, int timestamp) {}
}
