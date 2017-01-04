package com.muchencute.commons.session;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class FakeSession extends AbstractSession {

    private Map<String, Object> mStringObjectMap = new HashMap<>();

    @Override
    void nativePut(String key, Object value) {
        mStringObjectMap.put(key, value);
    }

    @Override
    Object nativeGet(String key) {
        return mStringObjectMap.get(key);
    }

    @Override
    void nativeRemove(String key) {
        mStringObjectMap.remove(key);
    }
}
