package com.muchencute.commons.session;

@SuppressWarnings("ALL")
public abstract class AbstractSession {

    abstract void nativePut(String key, Object value);

    abstract Object nativeGet(String key);

    abstract void nativeRemove(String key);

    public boolean hasAndNotNull(String key) {
        return key != null && nativeGet(key) != null;
    }

    public boolean equal(String key, Object value) {
        return key != null && value != null && value.equals(nativeGet(key));
    }

    public boolean in(String key, Object... objects) {

        if (key == null) return false;

        Object value = get(key);
        if (value == null) return false;

        for (Object object : objects) {
            if (object.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public Object get(String key) {
        return key == null ? null : nativeGet(key);
    }

    public AbstractSession put(String key, Object value) {
        if (key != null && value != null) {
            nativePut(key, value);
        }
        return this;
    }

    public AbstractSession remove(String key) {
        if (key != null) {
            nativeRemove(key);
        }
        return this;
    }
}
