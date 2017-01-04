package com.muchencute.commons.protocol;

import org.json.JSONArray;

@SuppressWarnings("ALL")
public final class JERArray extends AbstractJER<JSONArray> {

    public JERArray() {
        super();
    }

    @Override
    void initResult() {
        mResult = new JSONArray();
    }
}
