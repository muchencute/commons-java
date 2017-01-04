package com.muchencute.commons.database;

@SuppressWarnings("ALL")
public class OutParam {

    private int mType;

    private Object mValue = null;

    public OutParam(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public Object getValue() {
        return mValue;
    }

    public void setValue(Object value) {
        mValue = value;
    }
}
