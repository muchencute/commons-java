package com.muchencute.commons.protocol;

import org.json.JSONObject;

@SuppressWarnings("ALL")
public final class JERObject extends AbstractJER<JSONObject> {

    public JERObject() {
        super();
    }

    @Override
    void initResult() {
        mResult = new JSONObject();
    }
}
